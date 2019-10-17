import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Observable} from "rxjs";
import {AuthService} from "../../shared/services/auth.service";
import {TaskService} from "../../shared/services/task.service";

@Component({
  selector: 'app-order-form',
  templateUrl: './order-form.component.html',
  styleUrls: ['./order-form.component.scss']
})
export class OrderFormComponent implements OnInit {
  currentDate: Date;
  maxDate = new Date();
  form: FormGroup;

  universityList$: Observable<any>;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  thirdFormGroup: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private taskService: TaskService,
  ) { }

  ngOnInit() {
    this.universityList$ =  this.authService.getUniversityList();
    this.currentDate = new Date();
    this.maxDate.setDate(this.maxDate.getDate() + 90);

    // localStorage.getItem()
    this.firstFormGroup = this.formBuilder.group({
      name: ['', Validators.required],
      surname: ['', Validators.required],
      // university: ['', Validators.required],
      course: ['', Validators.required],
    });
    this.secondFormGroup = this.formBuilder.group({
      subject: ['', Validators.required],
      type: ['', Validators.required],
      smallDescription: ['', Validators.required],
      fullDescription: ['', Validators.required],
      deadline: ['', Validators.required],
    });
    this.thirdFormGroup = this.formBuilder.group({
      isMyUniversity: [true, Validators.required],
      price: ['', Validators.required],
    });
  }

  submit() {
    const order = {
      name: this.firstFormGroup.value.name,
      surname: this.firstFormGroup.value.surname,
      course: this.firstFormGroup.value.course,
      deadline: this.secondFormGroup.value.deadline,
      type: this.secondFormGroup.value.type,
      smallDescription: this.secondFormGroup.value.smallDescription,
      fullDescription: this.secondFormGroup.value.fullDescription,
      isMyUniversity: this.thirdFormGroup.value.isMyUniversity,
      price: this.thirdFormGroup.value.price,
    };
    this.taskService.createOrder(order)
      .subscribe();
  }
}
