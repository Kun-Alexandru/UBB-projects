import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-confirmation-dialog',
  template: `
    <h1 id = "div1" mat-dialog-title>Confirm Deletion</h1>
    <div mat-dialog-content>
      <p>Are you sure you want to delete this document?</p>
    </div>
    <div mat-dialog-actions>
      <button mat-button (click)="onYesClick()">Yes</button>
      <button mat-button (click)="onNoClick()">No</button>
    </div>
  `,
  styles: [
    `
    #div1{
      background-color: #f54c28;
      color: #fcfcfc;
    }
    `
  ]
})
export class ConfirmationDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<ConfirmationDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  onNoClick(): void {
    this.dialogRef.close(false);
  }

  onYesClick(): void {
    this.dialogRef.close(true);
  }
}
