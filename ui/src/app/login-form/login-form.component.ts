import { Component, OnInit } from '@angular/core'
import { FormBuilder, FormGroup, Validators } from '@angular/forms'
import { MatDialog } from '@angular/material/dialog'
import { Router } from '@angular/router'
import { MessageComponent } from '../message/message.component'
import { UserService } from '../user.service'

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss'],
})
export class LoginFormComponent implements OnInit {
  loginForm: FormGroup = this.fb.group({
    email: ['Nag@gmail.com', [Validators.required, Validators.email]],
    password: ['12345678', [Validators.required]],
  })

  handleLogin(event: any): void {
    event.preventDefault()
    this.userService.login(this.loginForm.value).subscribe({
      next: (data: any) => {
        this.router.navigate(['/'])
      },
      error: (error: any) => {
        this.dialog.open(MessageComponent, {
          data: {
            message: error.error.messsage,
          },
        })
      },
    })
  }

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private userService: UserService,
    public dialog: MatDialog,
  ) {}

  ngOnInit(): void {}
}
