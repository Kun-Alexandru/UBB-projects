import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-document-update',
  templateUrl: './document-update.component.html',
  styleUrls: ['./document-update.component.css']
})
export class DocumentUpdateComponent implements OnInit, OnDestroy {
  documentId: string | undefined;
  title: string = '';
  author: string = '';
  numberOfPages: number | undefined;
  type: string = '';
  format: string = '';
  private queryParamSub: Subscription;

  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router) {
    this.queryParamSub = this.route.queryParams.subscribe(params => {
      this.documentId = params['id'];
      this.getDocument(this.documentId);
    });
  }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    this.queryParamSub.unsubscribe();
  }


  goToHome() {
    this.router.navigate(['/home']);
  }

  getDocument(id: string | undefined): void {
    this.http.get(`http://localhost/LAB7%20-%20ANGULAR,%20PHP/php/get.php?id=${id}`).subscribe((response: any) => {
      this.title = response.title;
      this.author = response.author;
      this.numberOfPages = response.numberOfPages;
      this.type = response.type;
      this.format = response.format;
    }, (error) => {
      console.log(error);
    });
  }

  updateDocument(): void {
    if (!this.title) {
      alert('Please enter a title.');
      return;
    }

    if (!this.author) {
      alert('Please enter an author.');
      return;
    }

    if (this.numberOfPages !== undefined && isNaN(this.numberOfPages)) {
      alert('Please enter a valid number for the number of pages.');
      return;
    } else if (this.numberOfPages !== undefined && this.numberOfPages <= 0) {
      alert('Please enter a non-null positive number for the number of pages.');
      return;
    }

    if (!this.format) {
      alert('Please enter a format.');
      return;
    }

    if (!this.type) {
      alert('Please enter a type.');
      return;
    }

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    const data = JSON.stringify({
      title: this.title,
      author: this.author,
      numberOfPages: this.numberOfPages,
      type: this.type,
      format: this.format
    });

    this.http.put(`http://localhost/LAB7%20-%20ANGULAR,%20PHP/php/edit.php?id=${this.documentId}`, data, httpOptions).subscribe({
      next: (response: any) => {
        alert(response.message);
        this.router.navigate(['/home']);
      },
      error: (error) => {
        console.log(error);
        alert(error.message);
      },
      complete: () => {
        console.log('Request completed.');
      }
    });
  }
}
