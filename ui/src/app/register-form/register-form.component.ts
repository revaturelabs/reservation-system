import { Component, OnInit } from '@angular/core'
import { FormBuilder, FormGroup, Validators } from '@angular/forms'
import { Router } from '@angular/router'
import { UserService } from '../user.service'

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.scss'],
})
export class RegisterFormComponent implements OnInit {
  registerForm: FormGroup = this.fb.group({
    email: ['Nag@gmail.com', [Validators.required, Validators.email]],
    password: ['12345678', [Validators.required]],
    name: ['NAG', [Validators.required]],
    mobile: ['1234567890', [Validators.required]],
    dob: [new Date(1983, 9, 5), [Validators.required]],
    gender: ['MALE', [Validators.required]],
    address: ['Universe', [Validators.required]],
  })

  handleRegister(event: any) {
    this.userService.save(this.registerForm.value).subscribe({
      next: (response: any) => {
        this.router.navigate(['/booking'])
      },
      error: (error: any) => {
        console.log(error)
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
