import { Pipe, PipeTransform } from '@angular/core';
import * as moment from 'moment';

@Pipe({
  name: 'duration',
})
export class DurationPipe implements PipeTransform {

  transform(isoDuration: string): string {
    let duration = moment.duration(isoDuration);
    return duration.hours() + 'h ' + duration.minutes() + 'min';
  }

}
