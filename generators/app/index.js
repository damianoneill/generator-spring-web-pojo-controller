'use strict';
var yeoman = require('yeoman-generator');

module.exports = yeoman.generators.Base.extend({
  constructor: function () {
    yeoman.generators.Base.apply(this, arguments);

    // This method adds support for a `--hateoas` flag
    this.option('hateoas');
    // And you can then access it later on this way; e.g.
    // this.scriptSuffix = (this.options.hateoas ? ".hateoas": ".js");
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
    var packagePath = this.properties.packageName.replace(/\./g, '/');
    var nounLowercase = this.properties.noun.toLowerCase();
    var nounLowercasePlural = nounLowercase + 's';

    this.fs.copyTpl(
      this.templatePath('ClientErrorInformation.java'),
      this.destinationPath(packagePath + '/ClientErrorInformation.java'), {
        packageName: this.properties.packageName
      }
    );
    this.fs.copyTpl(
      this.templatePath('Controller.java'),
      this.destinationPath(packagePath + '/Controller.java'), {
        packageName: this.properties.packageName
      }
    );
    this.fs.copyTpl(
      this.templatePath('CrudController.java'),
      this.destinationPath(packagePath + '/CrudController.java'), {
        packageName: this.properties.packageName
      }
    );
    this.fs.copyTpl(
      this.templatePath('NounController.java'),
      this.destinationPath(packagePath + '/' + nounLowercase + '/' + this.properties.noun + 'Controller.java'), {
        packageName: this.properties.packageName,
        noun: this.properties.noun,
        nounLowercase: nounLowercase,
        nounLowercasePlural: nounLowercasePlural,
        type: this.properties.type
      }
    );
    this.fs.copyTpl(
      this.templatePath('NounService.java'),
      this.destinationPath(packagePath + '/' + nounLowercase + '/' + this.properties.noun + 'Service.java'), {
        packageName: this.properties.packageName,
        noun: this.properties.noun,
        nounLowercase: nounLowercase,
        nounLowercasePlural: nounLowercasePlural,
        type: this.properties.type
      }
    );
  }
});
