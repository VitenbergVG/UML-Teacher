import { preserveWhitespacesDefault } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { ChartDataSets, ChartOptions } from 'chart.js';
import { Color, Label } from 'ng2-charts';

@Component({
  selector: 'home-statistics',
  templateUrl: './home-statistics.component.html',
  styleUrls: ['./home-statistics.component.less']
})
export class HomeStatisticsComponent implements OnInit {

  lineChartData: ChartDataSets[] = [
    { data: [0, 1.5, 2.5, 1, 4, 3, 2] },
  ];

  lineChartLabels: Label[] = ['mon', 'tue', 'wed', 'thu', 'fri', 'sat', 'sun'];

  lineChartOptions: ChartOptions = {
    responsive: true,
    elements: {
      point: {
        backgroundColor: '#000000',
        radius: 4
      },
      line: {
        fill: false,
        tension: 0.7
      },
    },
    scales: {
      xAxes: [{
        gridLines: {
          display: false
        },
      }],
      yAxes: [{
        gridLines: {
          color: '#F5F5F7',
        }
      }]
    },
    tooltips: {
      backgroundColor: '#fff',
      cornerRadius: 4,
      bodyFontFamily: "'Baloo Bhaina 2', cursive",
      titleFontSize: 0,
      bodyFontSize: 14,
      bodyFontColor: '#000000',
      bodyAlign: 'center',
      intersect: false,
      displayColors: false
    }
  };

  lineChartColors: Color[] = [
    {
      borderWidth: 2,
      borderColor: 'black',
      borderJoinStyle: 'round'
    },
  ];

  constructor() { }

  ngOnInit() {
  }

}
