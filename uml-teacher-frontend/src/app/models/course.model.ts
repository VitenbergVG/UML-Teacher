import { EducationMaterialModel } from "./education-material.model";
import { TaskModel } from "./task.model";

export class CourseModel {

  id: number;
  name: string;
  createdDate: Date;
  rating: number;
  timeToComplete: string;
  complete?: number;
  task: TaskModel[];
  educationMaterials: EducationMaterialModel[];
}
