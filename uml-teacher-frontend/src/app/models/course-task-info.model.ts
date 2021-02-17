import { TaskType } from "./task-type.enum";
import { TestCourseTaskModel } from "./test-course-task.model";

export class CourseTaskInfoModel {

  taskNumber: number;
  type: TaskType;
  question?: string;
  answer?: string;
  testTasks?: TestCourseTaskModel[];
  //TODO add reciever byte array or blob for graphic tasks
} 
