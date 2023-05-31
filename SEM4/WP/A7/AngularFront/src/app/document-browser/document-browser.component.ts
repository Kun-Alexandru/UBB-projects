import { HttpClient } from '@angular/common/http';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationDialogComponent } from '../confirmation-dialog/confirmation-dialog.component';

@Component({
  selector: 'app-document-browser',
  templateUrl: './document-browser.component.html',
  styleUrls: ['./document-browser.component.css']
})
export class DocumentBrowserComponent implements OnInit, OnDestroy {
  types: string[] = [];
  formats: string[] = [];
  selectedType: string = '';
  selectedFormat: string = '';
  documents: any[] = [];
  filterUsed: string = '';
  previousFilter: string = '';

  private subscriptions: Subscription[] = [];

  constructor(private http: HttpClient, private router: Router,  public dialog: MatDialog) { }

  ngOnInit(): void {
    this.subscriptions.push(this.populateSelectType());
    this.subscriptions.push(this.populateSelectFormat());
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  populateSelectType(): Subscription {
    return this.http.get<any[]>('http://localhost/LAB7%20-%20ANGULAR,%20PHP/php/getByType.php?type=')
      .subscribe((data: any) => {
        this.types = data.map((item: { type: any; }) => item.type);
      });
  }

  populateSelectFormat(): Subscription {
    return this.http.get<any[]>('http://localhost/LAB7%20-%20ANGULAR,%20PHP/php/getByFormat.php?format=')
      .subscribe(data => {
        this.formats = data.map(item => item.format);
      });
  }

  openConfirmationDialog(documentId: string): void {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      width: '250px',
      data: {}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.http.delete(`http://localhost/LAB7%20-%20ANGULAR,%20PHP/php/delete.php?id=${documentId}`).subscribe(() => {
          if(this.selectedFormat)
            this.onFormatChange();
          else
            this.onTypeChange();
        });
      }
    });
  }

  goToHome() {
    this.router.navigate(['/home']);
  }

  populateTable(link: string) {
    this.subscriptions.push(this.http.get<any[]>(link)
      .subscribe(data => {
        this.documents = data;
      }));
  }

  onTypeChange() {
    const link = "http://localhost/LAB7%20-%20ANGULAR,%20PHP/php/getByType.php?type=" + this.selectedType;
    this.documents = [];
    this.populateTable(link);
    this.selectedFormat = '';
    this.previousFilter = this.filterUsed;
    this.filterUsed = "Previous filter - Type: " + this.selectedType;
  }

  onFormatChange() {
    const link = "http://localhost/LAB7%20-%20ANGULAR,%20PHP/php/getByFormat.php?format=" + this.selectedFormat;
    this.documents = [];
    this.populateTable(link);
    this.selectedType = '';
    this.previousFilter = this.filterUsed;
    this.filterUsed = "Previous filter - Format: " + this.selectedFormat;
  }
}
