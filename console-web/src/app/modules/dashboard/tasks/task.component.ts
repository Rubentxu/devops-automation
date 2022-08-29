import {Component, Input, OnInit} from '@angular/core';
import {AutomatedTask} from "../../../model/task/entities";


@Component({
  selector: 'task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.scss']
})
export class TaskComponent implements OnInit {
  // @ts-ignore
  @Input() task: AutomatedTask;

  constructor() { }

  ngOnInit(): void {
  }

}
