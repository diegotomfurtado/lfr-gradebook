package com.liferay.training.gradebook.service.impl;

import java.util.Date;
import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.training.gradebook.model.Assignment;
import com.liferay.training.gradebook.model.Submission;
import com.liferay.training.gradebook.service.base.SubmissionLocalServiceBaseImpl;

public class SubmissionLocalServiceImpl extends SubmissionLocalServiceBaseImpl {

    @Override
    public Submission addSubmission(long assignmentId, long studentId, String submissionText,
            ServiceContext serviceContext) throws PortalException {

        //
        // ( 1 ) - Get Assignment.
        //

        Assignment assignment = assignmentLocalService.getAssignment(assignmentId);

        //
        // ( 2 ) - Get user.
        //
        // Even though we will be not using the user object in this method, fetching it validates 
        // its existence.
        //

        long userId = serviceContext.getUserId();

        User user = userLocalService.getUser(userId);

        //
        // ( 3 ) - Get student user (studentId).
        //
         // Even though we will be not using the user object in this method, fetching it validates 
        // its existence.
        //

        User studentUser = userLocalService.getUser(studentId);

        //
        // ( 4 ) - Generate submission id
        //

        long submissionId = counterLocalService.increment(Submission.class.getName());

        //
        // ( 5 ) - Create Submission
        //

        Submission submission = submissionLocalService.createSubmission(submissionId);

        // Populate submission fields

        submission.setSubmissionId(submissionId);
        submission.setAssignmentId(assignmentId);
        submission.setCompanyId(assignment.getCompanyId());
        submission.setGroupId(assignment.getGroupId());
        submission.setCreateDate(new Date());
        submission.setModifiedDate(new Date());

        submission.setUserId(userId);
        submission.setGrade(-1);
        submission.setStudentId(studentId);
        submission.setSubmissionText(submissionText);
        submission.setSubmitDate(new Date());

        //
        // ( 6 ) - Persist the entity.
        //

        submission = super.addSubmission(submission);

        //
        // ( 7 ) - Return the updated Submission.
        //

        return submission;
    }

    public List<Submission> getSubmissionsByAssignment(long groupId, long assignmentId) {

        //
        // ( 8 ) - Get submissions by groupId.
        //

        return submissionPersistence.findByG_A(groupId, assignmentId);
    }

    public List<Submission> getSubmissionsByAssignment(long groupId, long assignmentId, int start, int end) {

        //
        // ( 9 ) - Get submissions by groupId and assignmentId.
        //

        return submissionPersistence.findByG_A(groupId, assignmentId, start, end);
    }

    public int getSubmissionsCountByAssignment(long groupId, long assignmentId) {

        //
        // ( 10 ) - Get count by groupId and assignmentId.
        //

        return submissionPersistence.countByG_A(groupId, assignmentId);
    }

    public Submission gradeAndCommentSubmission(long submissionId, int grade, String comment) throws PortalException {

        //
        // ( 11 ) - Get Submission.
        //

        Submission submission = this.getSubmission(submissionId);    

        //
        // ( 12 ) - Update following fields: grade, comment, modifiedDate.
        //
        submission.setGrade(grade);
        submission.setComment(comment);
        submission.setModifiedDate(new Date());

        //
        // ( 13 ) - Return updated submission.
        //

        return super.updateSubmission(submission);
    }

    public Submission gradeSubmission(long submissionId, int grade) throws PortalException {

        //
        // ( 14 ) - Get Submission.
        //

        Submission submission = this.getSubmission(submissionId);

        //
        // ( 15 ) - Update following fields: grade, modifiedDate.
        //

        submission.setGrade(grade);
        submission.setModifiedDate(new Date());


        //
        // ( 16 ) - Return updated submission.
        //

        return super.updateSubmission(submission);
    }

    @Override
    public Submission updateSubmission(long submissionId, String submissionText, ServiceContext serviceContext)
            throws PortalException {

        //
        // ( 17 ) - Get Submission.
        //

        Submission submission = this.getSubmission(submissionId);

        //    
        // ( 18 ) - Update following fields: submissionText, submitDate, modifiedDate.
        //

        submission.setSubmissionText(submissionText);
        submission.setSubmitDate(new Date());
        submission.setModifiedDate(new Date());

        //
        // ( 19 ) - Persist the entity.
        //

        submission = super.updateSubmission(submission);

        //
        // ( 20 ) - Return updated submission.
        //

        return submission;
    }
} 