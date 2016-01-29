'use strict';
var path = require('path');
var assert = require('yeoman-assert');
var helpers = require('yeoman-generator').test;

describe('generator-spring-web-pojo-repository-exporter:app', function () {
  before(function (done) {
    helpers.run(path.join(__dirname, '../generators/app'))
      .withOptions({someOption: true})
      .withPrompts({someAnswer: true})
      .on('end', done);
  });

  it('creates files', function () {
    assert.file([
      'com/example/demo/Controller.java'
    ]);
    assert.file([
      'com/example/demo/CrudController.java'
    ]);
    assert.file([
      'com/example/demo/person/PersonController.java'
    ]);
    assert.file([
      'com/example/demo/person/PersonService.java'
    ]);
  });
});
