import {Directive, ElementRef} from "@angular/core";

@Directive({
  selector: '[greencolor]',
})
export class GreencolorDirective {
  constructor(
    private el: ElementRef,
  ){
    el.nativeElement.style.color = '#0F9D58';
    // el.nativeElement.style.color = '#13fc03';
  }
}
