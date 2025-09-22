import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { BackendDisegnoService } from '../../services/backend-disegno-service';

@Component({
  selector: 'app-disegni',
  standalone: false,
  templateUrl: './disegni.html',
  styleUrl: './disegni.css'
})
export class Disegni implements OnInit{
  response:any;
  disegni:any;

  constructor(private service:BackendDisegnoService,
    private cdr: ChangeDetectorRef){}

  ngOnInit(): void {
    console.log("ngOnInit")
    this.service.listDisegni()
      .subscribe((resp:any) => {
        this.response = resp;
        console.log(resp);
        this.disegni = this.response.list;
        this.cdr.detectChanges();
      });
  }
}
