import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationDialogComponent } from '../confirmation-dialog/confirmation-dialog.component';

@Component({
  selector: 'app-document-home',
  templateUrl: './document-home.component.html',
  styleUrls: ['./document-home.component.css']
})
export class DocumentHomeComponent implements OnInit, OnDestroy {
  documents: any[] = [];
  documents2: any[] = [];
  subscription!: Subscription;

  constructor(private http: HttpClient, private router: Router, public dialog: MatDialog) { }

  onAddClick(): void {
    this.router.navigate(['/add']);
  }

  onDeleteClick(documentId: string): void {
    this.router.navigate(['/delete', documentId]);
  }

  onBrowserClick(): void {
    this.router.navigate(['/browse']);
  }

  fetchDocuments(): void {
    this.subscription = this.http.get<Document[]>('http://localhost/LAB7%20-%20ANGULAR,%20PHP/php/getAll.php').subscribe(documents => {
      this.documents = documents;
    });
    this.subscription = this.http.get<Document[]>('http://localhost/LAB7%20-%20ANGULAR,%20PHP/php/top3.php').subscribe(documents2 => {
      this.documents2 = documents2;
    });
  }

  onDeleteClick2(id: string) {
    this.openConfirmationDialog(id);
  }

  openConfirmationDialog(documentId: string): void {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      width: '250px',
      data: {}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.http.delete(`http://localhost/LAB7%20-%20ANGULAR,%20PHP/php/delete.php?id=${documentId}`).subscribe(() => {
          this.fetchDocuments();
          this.router.navigate(['/home']);
        });
      }
    });
  }

  ngOnInit() {
    this.subscription = this.http.get<any[]>('http://localhost/LAB7%20-%20ANGULAR,%20PHP/php/getAll.php').subscribe(data => {
      this.documents = data;
    });
    this.subscription = this.http.get<any[]>('http://localhost/LAB7%20-%20ANGULAR,%20PHP/php/top3.php').subscribe(data2 => {
      this.documents2 = data2;
    });
  }

  ngOnDestroy() {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
