import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-document-add',
  templateUrl: './document-add.component.html',
  styleUrls: ['./document-add.component.css']
})
export class DocumentAddComponent implements OnInit, OnDestroy {
  title: string = '';
  author: string = '';
  numberOfPages: number | undefined;
  type: string = '';
  format: string = '';
  httpSubscription: Subscription | undefined;

  constructor(private router: Router, private http: HttpClient) { }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    if (this.httpSubscription) {
      this.httpSubscription.unsubscribe();
    }
  }

  goToHome() {
    this.router.navigate(['/home']);
  }

  addDocument() {
    // Frontend validation using HTML5 form validation
    const form = document.getElementById('addForm') as HTMLFormElement;
    if (!form.checkValidity()) {
      alert('Invalid input. Please check the form again.');
      return;
    }

    if (!this.title) {
      alert('Please enter a title.');
      return;
    }

    if (!this.author) {
      alert('Please enter an author.');
      return;
    }

    if (!this.type) {
      alert('Please enter a type.');
      return;
    }

    if (this.numberOfPages !== undefined && isNaN(this.numberOfPages)) {
      alert('Please enter a valid number for the number of pages.');
      return;
    } else if (this.numberOfPages !== undefined && this.numberOfPages <= 0) {
      alert('Please enter a non-null positive number for the number of pages.');
      return;
    }

    if(!this.format){
      alert('Please enter a format.');
      return;
    }
    
    const postData = {
      title: this.title,
      author: this.author,
      numberOfPages: this.numberOfPages,
      type: this.type,
      format: this.format
    };

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    this.httpSubscription = this.http.post<any>('http://localhost/LAB7%20-%20ANGULAR,%20PHP/php/add.php', postData, httpOptions)
      .subscribe(
        response => {
          alert(response.message);
          this.router.navigate(['/home']);
        },
        error => {
          alert(error.error.message);
        }
      );
  }
}
