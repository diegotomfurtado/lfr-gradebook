create index IX_5FBC35BE on gradebook_Assignment (groupId, status);
create index IX_FC5A66F8 on gradebook_Assignment (status);
create index IX_35993B86 on gradebook_Assignment (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_1D26EB88 on gradebook_Assignment (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_3C5CE5F5 on gradebook_Submission (groupId, assignmentId);
create index IX_56D7CB71 on gradebook_Submission (studentId, assignmentId);