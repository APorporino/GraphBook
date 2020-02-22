import {Component} from '@angular/core';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'graph-book';

  constructor(public dialog: MatDialog) {}

  openDialog(): void {
    const dialogRef = this.dialog.open(LogoutDialog, {
      width: '400px',
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }
}

@Component({
  selector: 'logout-dialog',
  templateUrl: 'logout-dialog.html',
})
export class LogoutDialog {
  constructor(
      public dialogRef: MatDialogRef<LogoutDialog>) {}

  onNoClick(): void {
    this.dialogRef.close();
  }
  //TODO: MUST TIE TO BACKEND TO ACTUALLY LOGOUT
  logout(): void {
    this.dialogRef.close();
  }

}



