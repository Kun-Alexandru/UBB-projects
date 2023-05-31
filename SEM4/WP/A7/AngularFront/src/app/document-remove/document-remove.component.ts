import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-document-remove',
  templateUrl: './document-remove.component.html',
  styleUrls: ['./document-remove.component.css']
})
export class DocumentRemoveComponent implements OnInit, OnDestroy {
  documentId: string | undefined;
  private queryParamSub: Subscription = Subscription.EMPTY;

  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    this.queryParamSub = this.route.queryParams.subscribe(params => {
      this.documentId = params['id'];
      console.log(this.documentId);
    });
  }

  ngOnDestroy(): void {
    this.queryParamSub.unsubscribe();
  }

  onDeleteConfirmed(): void {
    this.http.delete(`http://localhost/LAB7%20-%20ANGULAR,%20PHP/php/delete.php?id=${this.documentId}`).subscribe(() => {
      this.router.navigate(['/home']);
    });
  }

  onCancel(): void {
    this.router.navigate(['/home']);
  }
}
