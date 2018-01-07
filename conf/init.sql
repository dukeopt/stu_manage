
LOCK TABLES `admin` WRITE;
INSERT INTO `admin` (`id`, `name`, `password`)
  VALUES
    (1,'admin','admin');
UNLOCK TABLES;

LOCK TABLES `course` WRITE;
INSERT INTO `course` (`id`, `name`, `vaild`)
  VALUES
    (1,'计算机',1);
UNLOCK TABLES;

LOCK TABLES `course_student` WRITE;
UNLOCK TABLES;

LOCK TABLES `course_teacher` WRITE;
INSERT INTO `course_teacher` (`id`, `course_id`, `teacher_id`)
  VALUES
    (1,1,1);
UNLOCK TABLES;

LOCK TABLES `homework` WRITE;
INSERT INTO `homework` (`id`, `course_teacher_id`, `name`, `path`, `date`)
  VALUES
    (1,1,'计算机-作业1','teacher/1/2017127-0908011234/教师作业1.txt','2017-12-7 09:08');
UNLOCK TABLES;

LOCK TABLES `score` WRITE;
INSERT INTO `score` (`id`, `score`, `student_homework_path`, `student_id`, `homework_id`)
  VALUES
    (1,99,'student/1/2017127-0908011234/学生作业1.txt',1,1),
    (2,NULL ,'student/2/2017127-0908011234/学生作业2.txt',2,1);

UNLOCK TABLES;

LOCK TABLES `student` WRITE;
INSERT INTO `student` (`id`, `name`, `password`)
  VALUES
    (1,'student','student'),
    (2,'student2','student2');
UNLOCK TABLES;

LOCK TABLES `teacher` WRITE;
INSERT INTO `teacher` (`id`, `name`, `password`)
  VALUES
    (1,'teacher','teacher');
UNLOCK TABLES;
