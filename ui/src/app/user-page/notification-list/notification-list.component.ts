import {Component, Inject, Input, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material";
import {Message} from "../../shared/interfaces";



@Component({
  selector: 'app-notification-list',
  templateUrl: './notification-list.component.html',
  styleUrls: ['./notification-list.component.scss']
})
export class NotificationListComponent implements OnInit {

  @Input() messages: Message[];

  constructor(
    public dialog: MatDialog,
  ) { }

  ngOnInit() {
  }

  openNoti(title, text, sender) {
    const dialogRef = this.dialog.open(NotificationModal, {
      width: '250px',
      data: {title: title, text: text, sender: sender}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      console.log(result);
      // this.animal = result;
    });
  }
}

@Component({
  selector: 'notification-modal.html',
  templateUrl: 'notification-modal.html',
})
export class NotificationModal {
  constructor(
    public dialogRef: MatDialogRef<NotificationModal>,
    @Inject(MAT_DIALOG_DATA) public data) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

}
