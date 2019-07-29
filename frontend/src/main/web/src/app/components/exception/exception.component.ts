import {Component, Input, OnInit} from "@angular/core";

@Component({
  selector: "exception",
  templateUrl: "./exception.component.html"
})
export class ExceptionComponent implements OnInit {

  @Input('errorMessage') errorMessage: string;

  constructor() {
  }

  ngOnInit() {
  }
}
