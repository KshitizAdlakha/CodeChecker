'use strict';

/* https://github.com/angular/protractor/blob/master/docs/toc.md */

describe('code checker', function() {


  it('should automatically redirect to / when location hash/fragment is empty', function() {
    browser.get('index.html');
    expect(browser.getLocationAbsUrl()).toMatch("/");
  });


  describe('login', function() {

    beforeEach(function() {
      browser.get('/#/');
    });


    it('should render login page when user navigates to /', function() {
        console.log("1");
        expect(element.all(by.css('[ng-view] a')).first().getText()).toMatch(/Code Checker/);
    });

  });


  describe('sign up', function() {

    beforeEach(function() {
      browser.get('/#/sign-up');
    });

    it('should render sign up when user navigates to /sign-up', function() {
        expect(element(by.id('signUpButton')).getText()).toMatch(/SIGN UP/);
    });

  });
});
