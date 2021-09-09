import { PossibleAnwers } from './../common/models/possible-answer';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Product } from '../common/models/product-model';
import { Response } from '../common/models/response-model';
import { ProductService } from '../common/services/product.service';

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.scss']
})
export class QuestionComponent implements OnInit {

  public products: Product[];
  public response: Response = new Response();
  public answersToAge = this.possibleAnswers.answersToAge;
  public answersToIncome = this.possibleAnswers.answersToIncome;
  questionForm: FormGroup;

  constructor(private productService: ProductService,
              private possibleAnswers: PossibleAnwers,
              private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.findProducts();
    this.initForm();
    console.log(this.answersToAge);
    console.log(this.answersToIncome);
  }

  public findProducts(): void {
    this.productService.findProducts().subscribe(products => {
      console.log(products);
      if (products) {
        this.products = products;
      }
    });
  }

  public initForm(): void {
    this.questionForm = this.formBuilder.group({
      ageRange: new FormControl('', Validators.required),
      isStudent: new FormControl('', Validators.required),
      incomeRange: new FormControl('', Validators.required)
    });

    this.questionForm.get('ageRange').valueChanges.subscribe(value => {
      console.log(value);
      this.response.ageRange = value;
    });

    this.questionForm.get('isStudent').valueChanges.subscribe(value => {
      console.log(value);
      this.response.isStudent = value;
    });

    this.questionForm.get('incomeRange').valueChanges.subscribe(value => {
      console.log(value);
      this.response.incomeRange = value;
    });
  }

  public getRecomendation(): void {
    // TODO
    console.log(this.response);
  }

  public cancel(): void {
    // TODO
  }
}
