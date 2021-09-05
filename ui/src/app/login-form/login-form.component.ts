import { Component, OnInit } from '@angular/core'
import { FormBuilder, FormGroup, Validators } from '@angular/forms'
import { Router } from '@angular/router'
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
    })
  }

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private userService: UserService,
  ) {}

  ngOnInit(): void {}
}
