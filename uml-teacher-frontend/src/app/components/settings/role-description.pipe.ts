import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'roleDescription'
})
export class RoleDescriptionPipe implements PipeTransform {

  transform(role: string): string {
    switch (role) {
      case 'ADMIN':
        return 'Administrator';
      case 'USER':
        return 'User';
      case 'STUDENT':
        return 'Student';
      case 'EMPLOYEE':
        return 'Employee';
    }
  }
}
