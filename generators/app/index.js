'use strict';
var yeoman = require('yeoman-generator');
var pluralize = require('pluralize');

module.exports = yeoman.generators.Base.extend({
  constructor: function () {
    yeoman.generators.Base.apply(this, arguments);

    // This method adds support for outputing into a maven directory structure `--maven` flag
    this.option('maven');
    // And you can then access it later on this way; e.g.
    // this.scriptSuffix = (this.options.maven ? ".maven": ".notmaven");
  },

  prompting: function () {
    var done = this.async();

    var prompts = [{
      type: 'input',
      name: 'noun',
      message: 'What\'s the name of the noun to be modelled?',
      store: true,
      default: 'Person'
    }, {
      type: 'input',
      name: 'type',
      message: 'What\'s the type (if primitive, use its wrapper class) of the variable used as an identifier in the noun?',
      store: true,
      default: 'String'
    }, {
      type: 'confirm',
      name: 'filterYesNo',
      message: 'Would you like to apply a filter to the Nouns collection find methods?',
      store: true
    }, {
      when: function (response) {
        return response.filterYesNo;
      },
      type: 'input',
      name: 'filterName',
      message: 'What\'s the name of the variable used as an filter in the noun\'s collection?',
      store: true,
      default: 'age'
    }, {
      when: function (response) {
        return response.filterYesNo;
      },
      type: 'input',
      name: 'filterType',
      message: 'What\'s the type (if primitive, use its wrapper class) of the variable used as an filter in the noun\'s collection?',
      store: true,
      default: 'Integer'
    }, {
      type: 'input',
      name: 'packageName',
      message: 'What is your default Java package name?',
      validate: function (input) {
        if (/^([a-z_]{1}[a-z0-9_]*(\.[a-z_]{1}[a-z0-9_]*)*)$/.test(input)) {
          return true;
        }
        return 'The package name you have provided is not a valid Java package name.';
      },
      store: true,
      default: 'com.example.demo'
    }];

    this.prompt(prompts, function (props) {
      this.properties = props;

      done();
    }.bind(this));
  },

  writing: function () {
    var srcDir = './';
    // var testDir = './';
    if (this.options.maven) {
      console.log('Using Maven directory structure ...');
      srcDir = 'src/main/java/';
    }

    var packagePath = this.properties.packageName.replace(/\./g, '/');
    var nounLowercase = this.properties.noun.toLowerCase();
    var nounLowercasePlural = pluralize(nounLowercase);

    this.fs.copyTpl(
      this.templatePath('ClientErrorInformation.java'),
      this.destinationPath(srcDir + packagePath + '/ClientErrorInformation.java'), {
        packageName: this.properties.packageName
      }
    );
    this.fs.copyTpl(
      this.templatePath('CrudController.java'),
      this.destinationPath(srcDir + packagePath + '/CrudController.java'), {
        packageName: this.properties.packageName
      }
    );
    this.fs.copyTpl(
      this.templatePath('NounController.java'),
      this.destinationPath(srcDir + packagePath + '/' + nounLowercase + '/' + this.properties.noun + 'Controller.java'), {
        packageName: this.properties.packageName,
        noun: this.properties.noun,
        nounLowercase: nounLowercase,
        nounLowercasePlural: nounLowercasePlural,
        type: this.properties.type,
        filterName: this.properties.filterName,
        filterType: this.properties.filterType,
        filterYesNo: this.properties.filterYesNo
      }
    );
    this.fs.copyTpl(
      this.templatePath('NounService.java'),
      this.destinationPath(srcDir + packagePath + '/' + nounLowercase + '/' + this.properties.noun + 'Service.java'), {
        packageName: this.properties.packageName,
        noun: this.properties.noun,
        nounLowercase: nounLowercase,
        nounLowercasePlural: nounLowercasePlural,
        type: this.properties.type
      }
    );
  }
});
