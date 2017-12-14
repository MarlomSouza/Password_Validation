import { RouterModule, Routes } from "@angular/router";
import { AppComponent } from "./app.component";



const appRoutes: Routes = [

    {path: '', component: AppComponent},
    {path: '**', component: AppComponent},


];

export const rounting = RouterModule.forRoot(appRoutes);
