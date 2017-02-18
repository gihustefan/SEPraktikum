package at.decisionexpert.controller.comment;

/**
 * Created by stefanhaselboeck on 14.11.16.
 */
//@RestController
//@RequestMapping("api/dgm/{idDecisionGuidanceModel}/comments")
//@ResponseBody
//public class DecisionGuidanceModelCommentRelationControllerImpl implements CommentRelationController {
//
//    @Autowired
//    private CommentService commentService;
//
//    @Override
//    @RequestMapping(method = RequestMethod.POST)
//    public CommentRelationDto create(@PathVariable Long idDecisionGuidanceModel, @RequestBody CommentRelationChangeRequestDto commentValue, CommentStartNodeType type) {
//        return commentService.createCommentRelation(idDecisionGuidanceModel, commentValue, type);
//    }
//
//    @Override
//    @RequestMapping(value = "/{idCommentRelation}",method = RequestMethod.PATCH)
//    public CommentRelationDto updateAttributes(@PathVariable Long idDecisionGuidanceModel, @PathVariable Long idCommentRelation, @RequestBody CommentRelationChangeRequestDto newCommentValue) {
//        return commentService.updateExistingCommentRelationAttribute(idDecisionGuidanceModel, idCommentRelation, newCommentValue);
//    }
//
//    @Override
//    @RequestMapping(value = "/{idCommentRelation}",method = RequestMethod.DELETE)
//    public void delete(@PathVariable Long idDecisionGuidanceModel, @PathVariable Long idCommentRelation) {
//        commentService.deleteCommentRelationAttribute(idDecisionGuidanceModel, idCommentRelation);
//    }
//}
