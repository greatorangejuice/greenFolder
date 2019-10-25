import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Observable} from "rxjs";
import {AuthService} from "../../shared/services/auth.service";
import {TaskService} from "../../shared/services/task.service";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material";
import {Task} from "../../shared/interfaces";

@Component({
  selector: 'app-order-form',
  templateUrl: './order-form.component.html',
  styleUrls: ['./order-form.component.scss']
})
export class OrderFormComponent implements OnInit {
  currentDate: Date;
  maxDate = new Date();
  form: FormGroup;
  task: Task;

  universityList$: Observable<any>;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private taskService: TaskService,
    public dialog: MatDialog,
  ) { }

  ngOnInit() {
    this.universityList$ =  this.authService.getUniversityList();
    this.currentDate = new Date();
    this.maxDate.setDate(this.maxDate.getDate() + 90);

    this.firstFormGroup = this.formBuilder.group({
      university: ['', Validators.required],
      faculty: ['', Validators.required],
      course: ['', Validators.required],
    });
    this.secondFormGroup = this.formBuilder.group({
      name: ['', Validators.required],
      subject: ['', Validators.required],
      type: ['', Validators.required],
      description: ['', Validators.required],
      specification: ['', Validators.required],
      deadline: ['', Validators.required],
    });
  }

  // showOrder() {
  //   this.order = {
  //     name: this.firstFormGroup.value.name,
  //     surname: this.firstFormGroup.value.surname,
  //     course: this.firstFormGroup.value.course,
  //     deadline: this.secondFormGroup.value.deadline,
  //     type: this.secondFormGroup.value.type,
  //     smallDescription: this.secondFormGroup.value.smallDescription,
  //     fullDescription: this.secondFormGroup.value.fullDescription,
  //     isMyUniversity: this.thirdFormGroup.value.isMyUniversity,
  //     price: this.thirdFormGroup.value.price,
  //   };
  //   console.log(this.order);
  //
  // }

  submit() {
    this.task = {
      customer: localStorage.getItem('username'),
      course: this.firstFormGroup.value.course,
      university: this.firstFormGroup.value.university,
      // deadline: this.secondFormGroup.value.deadline,
      deadline: 'time',
      type: this.secondFormGroup.value.type,
      description: this.secondFormGroup.value.description,
      specification: this.secondFormGroup.value.specification,
      name: this.secondFormGroup.value.name,
      subject: this.secondFormGroup.value.subject,
      faculty: this.firstFormGroup.value.faculty,
      keywords: 'test',
    };
    this.taskService.createTask(this.task)
      .subscribe(
        (response) => {
          console.log(response);
        }
      );
  }

  showOrder(): void {
    this.task = {
      customer: localStorage.getItem('username'),
      course: this.firstFormGroup.value.course,
      university: this.firstFormGroup.value.university,
      deadline: this.secondFormGroup.value.deadline,
      type: this.secondFormGroup.value.type,
      description: this.secondFormGroup.value.description,
      specification: this.secondFormGroup.value.specification,
      name: this.secondFormGroup.value.name,
      subject: this.secondFormGroup.value.subject,
      faculty: this.firstFormGroup.value.faculty,
      keywords: 'test',
    };
    const dialogRef = this.dialog.open(PreSubmitForm, {
      width: '250px',
      data: {order: this.task}
    });

    dialogRef.afterClosed().subscribe(() => {
      this.submit()
    });
  }
}

@Component({
  selector: 'pre-submit-form',
  templateUrl: 'pre-submit-form.html',
})
export class PreSubmitForm {

  constructor(
    public dialogRef: MatDialogRef<PreSubmitForm>,
    @Inject(MAT_DIALOG_DATA) public data) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

}
