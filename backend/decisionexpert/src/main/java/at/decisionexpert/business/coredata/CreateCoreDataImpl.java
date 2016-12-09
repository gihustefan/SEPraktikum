package at.decisionexpert.business.coredata;

import at.decisionexpert.business.user.UserBusiness;
import at.decisionexpert.neo4jentity.node.CoreData;
import at.decisionexpert.repository.node.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by stefanhaselboeck on 11.10.16.
 */
@Component
public class CreateCoreDataImpl implements CreateCoreData {

    @Autowired
    private UserBusiness userBusiness;

    @Autowired
    private AttributeRepository attributeRepository;


    public <T extends CoreData> T createCoreData(String title, String description, Class<T> coreDataClass) {
        Assert.notNull(title);
        Assert.notNull(coreDataClass);

        try {
            Constructor constructor = coreDataClass.getConstructor();
            constructor.setAccessible(true);
            T newCoreData = (T) constructor.newInstance();

            newCoreData.setName(title);
            newCoreData.setDescription(description);
            newCoreData.setCreationDate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
            newCoreData.setLastModified(newCoreData.getCreationDate());
            newCoreData.setCreator(userBusiness.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));

            return attributeRepository.save(newCoreData);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
