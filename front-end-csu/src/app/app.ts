import { Component } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { MaterialModule } from './material.module';



@Component({
  standalone: true,
  selector: 'app-root',
  imports: [RouterOutlet, RouterModule, MaterialModule],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected title = 'front-end-csu';
}
