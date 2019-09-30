import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material";

export interface Messages {
  title: string;
  text: string;
}

@Component({
  selector: 'app-notification-list',
  templateUrl: './notification-list.component.html',
  styleUrls: ['./notification-list.component.scss']
})
export class NotificationListComponent implements OnInit {

  messages: Messages[] = [
    {
    title: 'Message title',
    text: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Asperiores consequuntur ducimus quae, tempore ullam vel.',
    },
    {
      title: 'Message title',
      text: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Asperiores consequuntur ducimus quae, tempore ullam vel.',
    }
  ];

  constructor(
    public dialog: MatDialog,
  ) { }

  ngOnInit() {
  }

  openNoti(text: string) {
    const dialogRef = this.dialog.open(NotificationModal, {
      width: '250px',
      data: {title: 'title', text: 'text'}
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
    @Inject(MAT_DIALOG_DATA) public data: Messages) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

}
