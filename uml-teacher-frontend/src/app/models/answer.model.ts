import { CourseTaskInfoModel } from "./course-task-info.model";
import { CourseModel } from "./course.model";
import { StudentModel } from "./student.model";

export class AnswerModel {

  id: number;
  studentId: number;
  taskId: number;
  courseId: number;
  answer: string;
  isCorrect: boolean;
  courseTask?: { [taskNumber: number]: string };
  student?: { [studentUserId: number]: string };
  course?: CourseModel;
}
