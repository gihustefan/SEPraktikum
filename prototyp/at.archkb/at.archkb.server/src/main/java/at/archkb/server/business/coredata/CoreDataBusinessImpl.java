package at.archkb.server.business.coredata;

import at.archkb.server.business.user.UserBusiness;
import at.archkb.server.neo4jentity.dto.APAttributeAdminDto;
import at.archkb.server.neo4jentity.dto.ApAttributeDetailAdminDto;
import at.archkb.server.neo4jentity.dto.ArchProfileRelationDto;
import at.archkb.server.neo4jentity.node.CoreData;
import at.archkb.server.neo4jentity.node.Tradeoff;
import at.archkb.server.neo4jentity.node.TradeoffItem;
import at.archkb.server.neo4jentity.node.User;
import at.archkb.server.repository.node.APAttributeRepository;
import at.archkb.server.repository.node.ArchProfileAttributeRepository;
import at.archkb.server.repository.node.TradeoffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Component
public class CoreDataBusinessImpl implements CoreDataBusiness {

    private static final String nodeentitypackage = "at.archkb.server.neo4jentity.node.";

    @Autowired
    private ArchProfileAttributeRepository coreDataRepository;

    @Autowired
    private APAttributeRepository apAttributeRepository;

    @Autowired
    private ArchProfileAttributeRepository archProfileAttributeRepository;

    @Autowired
    private UserBusiness userBusiness;

    @Autowired
    private TradeoffRepository tradeoffRepository;

    @Autowired
    private Neo4jOperations neo4jOperations;

    @Override
    @Transactional(readOnly = true)
    public <T extends CoreData> List<ArchProfileRelationDto> getCoreData(String titlePartial,
                                                                         Class<T> coreDataClass) {

        Assert.notNull(titlePartial);
        Assert.notNull(coreDataClass);

        List<ArchProfileRelationDto> result = new ArrayList<>();

        coreDataRepository.findAllByTitle(titlePartial, coreDataClass).forEach(core -> {
            result.add(new ArchProfileRelationDto(core));
        });

        return result;
    }

    @Override
    public Iterable<APAttributeAdminDto> getAllOfType(String type) {
        Assert.notNull(type);
        Assert.isTrue(type.length() > 3);
        return apAttributeRepository.getAllofType(type);
    }

    @Override
    public ApAttributeDetailAdminDto getById(Long id) {
        Assert.notNull(id);
        Assert.isTrue(id.longValue() > 0);
        return apAttributeRepository.getById(id);

    }

    @Override
    public ApAttributeDetailAdminDto addToCore(Long id) {
        //check if node with id is APAttribute
        Assert.notNull(apAttributeRepository.findOne(id, 0));

        return apAttributeRepository.addToCore(id);
    }

    @Override
    public ApAttributeDetailAdminDto removeFromCore(Long id) {
        //check if node with id is APAttribute
        Assert.notNull(apAttributeRepository.findOne(id, 0));

        return apAttributeRepository.removeFromCore(id);
    }

    @Override
    public ApAttributeDetailAdminDto updateAPAttribute(ApAttributeDetailAdminDto apat) {
        Assert.notNull(apat);
        Assert.isTrue(apat.getName().length() > 5);
        Assert.isTrue(apat.getDefinition().length() > 5);
        Assert.isTrue(apat.getId() > 0);

        CoreData at = apAttributeRepository.findOne(apat.getId());
        Assert.notNull(at);
        at.setName(apat.getName());
        at.setDefinition(apat.getDefinition());

        at = apAttributeRepository.save(at);
        apat.setName(at.getName());
        apat.setDefinition(at.getDefinition());
        return apat;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ApAttributeDetailAdminDto createAPAttribute(ApAttributeDetailAdminDto apat) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Assert.notNull(apat);
        Assert.notNull(apat.getType());
        Assert.isTrue(apat.getName().length() > 5);
        Assert.isTrue(apat.getDefinition().length() > 5);

        Class<? extends CoreData> clazz = (Class<? extends CoreData>) Class.forName(nodeentitypackage + apat.getType());
        CoreData at = clazz.newInstance();
        at.setName(apat.getName());
        at.setDefinition(apat.getDefinition());
        at.setCoreAdded(apat.getCoreAdded());
        at = apAttributeRepository.save(at);

        Assert.notNull(at);
        apat.setId(at.getId());
        return apat;
    }

    @Override
    public <T extends CoreData> T createCoreData(String title, String definition, Class<T> coreDataClass) {
        Assert.notNull(title);
        Assert.notNull(coreDataClass);

        try {
            Constructor constructor = coreDataClass.getConstructor();
            constructor.setAccessible(true);
            T newCoreData = (T) constructor.newInstance();

            newCoreData.setName(title);
            newCoreData.setDefinition(definition);
            newCoreData.setCreationDate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
            newCoreData.setLastModified(newCoreData.getCreationDate());
            newCoreData.setCreator(userBusiness.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));

            return apAttributeRepository.save(newCoreData);

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

    @Override
    @Transactional
    public Tradeoff getTradeoffByTradeoffItems(Long idTradeoffItemOver, Long idTradeoffItemUnder) {
        Assert.notNull(idTradeoffItemOver);
        Assert.notNull(idTradeoffItemUnder);


        Tradeoff tradeoff = tradeoffRepository.findTradeoffByTradeoffItems(idTradeoffItemOver, idTradeoffItemUnder);

        if (tradeoff != null)
            return tradeoff;

        // If tradeoff does not exist -> create a new one.
        User user = userBusiness.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        TradeoffItem over = archProfileAttributeRepository.findById(idTradeoffItemOver, TradeoffItem.class);
        Assert.notNull(over);

        TradeoffItem under = archProfileAttributeRepository.findById(idTradeoffItemUnder, TradeoffItem.class);
        Assert.notNull(under);

        return tradeoffRepository.save(new Tradeoff(user, over, under));
    }

}
