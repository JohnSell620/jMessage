var should = require('chai').should(),
    expect = require('chai').expect,
    supertest = require('supertest'),
    api = supertest('http://localhost:8080/jMessage/webapi');

describe('Profiles', function () {
  var user;
  var profile1;
  var profile2;
  var profile3;
  var profiles = [profile1, profile2, profile3];

  before(function (done) {

    api.post('/profiles')
      .set('Accept', 'application/x-www-form-urlencoded')
      .send({
        profileName: "james4",
        firstName: "james",
        lastName: "jameses",
        created: "2018-07-06 17:30:05"
      })
      .expect('Content-Type', /json/)
        .expect(200)
        .end(function (err, res) {
          profile1 = res.body;
      });


    api.post('/profiles')
      .set('Accept', 'application/x-www-form-urlencoded')
      .send({
        profileName: "max9",
        firstName: "max9",
        lastName: "maxes",
        created: "2018-07-06 17:31:05"
      })
      .expect('Content-Type', /json/)
        .expect(200)
        .end(function (err, res) {
          profile2 = res.body;
      });

    api.post('/profiles')
      .set('Accept', 'application/x-www-form-urlencoded')
      .send({
        profileName: "bill3",
        firstName: "bill",
        lastName: "bils",
        created: "2018-07-06 17:32:05"
      })
      .expect('Content-Type', /json/)
        .expect(200)
        .end(function (err, res) {
          profile3 = res.body;
      });

    api.post('/users')
      .set('Accept', 'application/x-www-form-urlencoded')
      .send({
        username: "max9",
        password: "password",
        pId: 6
      })
      .expect('Content-Type', /json/)
      .expect(200)
      .end(function (err, res) {
        user = res.body;
      });

  });

  it('should return a 200 response', function (done) {
    api.get('/users/john5')
      .set('Accept', 'application/json')
      .expect(200, done);
  });

  it('should be an object with keys and values', function (done) {
    api.get('/users/john5')
      .set('Accept', 'application/json')
      .expect(200)
      .end(function (err, res) {
        expect(res.body).to.have.property("id");
        expect(res.body.id).to.not.equal(null);
        expect(res.body).to.have.property("username");
        expect(res.body.username).to.not.equal(null);
        expect(res.body).to.have.property("password");
        expect(res.body.password).to.not.equal(null);
        expect(res.body).to.have.property("pId");
        expect(res.body.pId).to.not.equal(null);
        expect(res.body).to.have.property("profile");
        expect(res.body.pId).to.equal(null);
        done();
      });
  });

  it('username should be 5 characters in length', function (done) {
    api.get('/users/pat7')
      .set('Accept', 'application/json')
      .expect(200)
      .end(function (err, res) {
        expect(res.body.username.length).to.equal(5);
        done();
      });
  });

  it('john5\'s password should be \'password\'', function (done) {
    api.get('/users/1')
      .set('Accept', 'application/json')
      .expect(200)
      .end(function (err, res) {
        expect(res.body.password).to.equal("password");
        done();
      });
  });

  it('should be updated with a new name and password', function (done) {
    api.put('/users/max9')
      .set('Accept', 'application/x-www-form-urlencoded')
      .send({
        id: 5,
        username: "maxi9",
        password: "maxpass"
      })
      .expect(200)
      .end(function (err, res) {
        expect(res.body.id).to.equal(5);
        expect(res.body.username).to.equal("maxi9");
        expect(res.body.password).to.equal("maxpass");
        expect(res.body.pId).to.equal(6);
        expect(res.body.profile).to.equal(null);
        done();
      });
  });

});
