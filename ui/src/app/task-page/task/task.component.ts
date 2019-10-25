import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from '@angular/router';
import {TaskService} from "../../shared/services/task.service";
import {Observable} from "rxjs";
import {switchMap, tap} from "rxjs/operators";
import {CustomerResponse, Offer, Task} from '../../shared/interfaces'
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../shared/services/auth.service";

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class TaskComponent implements OnInit {

  id: string;
  task$: Observable<Task>;
  form: FormGroup;
  username = localStorage.getItem('username');
  isCustomer = false;

  constructor(
    private route: ActivatedRoute,
    private taskService: TaskService,
    private auth: AuthService,
  ) { }

  ngOnInit() {
    this.task$ = this.route.params
      .pipe(
        switchMap( (params:Params) => {
          this.id = params['id'];
          return this.taskService.getTaskById(this.id);
        } )
      );
    this.initForm();
    const permissions = this.auth.permissions();
    if (permissions.includes('EXECUTOR')) {
      this.isCustomer = true;
    };
  }

  initForm() {
    this.form = new FormGroup({
      bid: new FormControl('', [Validators.required]),
      comment: new FormControl('', [Validators.required, Validators.maxLength(100)]),
    })
  }

  submit() {
    const offer: Offer = {
      executor: this.username,
      secretId: this.id,
      bid: this.form.value.bid,
      comment: this.form.value.comment,
    };
    this.taskService.createOrder(offer)
      .subscribe(
        (response) => {
          console.log(response);
        }
      )
  }

  submitCustomerResponse(response) {
    const customerAnswer = {
      taskSecretId: this.id,
      offerId: response.offerId,
      customerResponse: response.answer,
      executor: response.executor,
    };
    this.taskService.submitCustomerResponse(customerAnswer)
      .subscribe(
        (response) => {
          console.log(response);
        }
      )
  }
}
