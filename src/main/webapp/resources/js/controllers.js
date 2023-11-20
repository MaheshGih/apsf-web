//var home_path = "/nfs/home/";
var home_path = $('#userHomePath').val();
var auth_user_id = $('#auth_user_id').val();
var CREATE = 'create';

/**
 * MainCtrl
 * @param $scope
 * @param $compile
 * @param $http
 * @param $q
 * @param UtilService]
 * @returns
 */
function MainCtrl( $scope, $rootScope, $compile, $http, $q, $location, $timeout, $cookies, UtilService, $rootScope, $interval) {
	
	$rootScope.makeItGo = function() {
	    // Setting values here in example, normally would come from some other component
	    $rootScope.playPosition = 0;
	    $rootScope.playDuration = 100;
	    //console.log('Beginning progress bar...');
	    var prog = $interval(function() {
	      if ($rootScope.playPosition < $rootScope.playDuration) {
	        $rootScope.playPosition += 1;
	      } else {
	        //console.log('Ending progress bar.');
	        $interval.cancel(prog);
	      }
	    }, 2);
	};
	
	$rootScope.loggedUser = loggedUser;
	
}
/**
 * RegistrationCtrl
 * @param $scope
 * @param UtilService
 * @returns
 */
function RegistrationCtrl($scope, $rootScope, $timeout, $filter, UtilService,$http,$state, $stateParams,$window) {
	
	var reg = this;
	
	//reg.id = $stateParams.id;
	reg.model = {id:$stateParams.id,'countryId':1};
		
	//load static data
	UtilService.postObject('registration/static/data',reg.model).then(function(response){
		reg.staticData =response;
		if(reg.staticData.user){
			$timeout(function(){
				reg.model = reg.staticData.user;
				reg.model.dob = $filter('date')(new Date(reg.model.dob), "yyyy-MM-dd");
			})
			
		}
				
		//if user logged in show current regid as reference id
		if(!reg.model.referedRegid && ($rootScope.loggedUser && $rootScope.loggedUser.id)){
			reg.model.referedRegid = $rootScope.loggedUser.regid;
		}
	});
	
	//reg.alert = { show:true, msg:'Registration done..!, APSF people will contact you shortly.' };
	reg.save = function(){
		
		var $regForm = $("#regForm");
		// Add Validations
		validateRegForm($regForm);
		if(!$regForm.valid())
			return;
		//dob = $filter('date')(new Date(reg.model.dob), "yyyy-MM-dd");
		if(reg.model.constituencyId){
			angular.forEach(reg.staticData.constituencies,function(constituency){
				if(reg.model.constituencyId==constituency.id){
					reg.model.districtId = constituency.districtId
				}
			});
		}
		
		if(reg.model.mandalId){
			angular.forEach(reg.staticData.villages,function(village){
				if(reg.model.villageId==village.id){
					reg.model.mandalId = village.id;
				}
			});
		}
		
		UtilService.postObject( 'user/rest/signup', reg.model ).then(function(response){
			console.log(response);
			reg.alert = { show:true, msg:'Your Reference is successfully completed..!, APSF people will contact shortly.' };
			UtilService.messageToaster('success',reg.alert.msg);
						
		});
	}
	
	function validateRegForm($form){
		var rules = $form.validate({
			ignore: ":hidden:not('input[type=text]')",
			
			rules:{
				'name' : {required:true},
				'phno':{ required:true, digits: true, minlength:10, maxlength:11 },
				'referedRegid':{required:true},
				
				'status':{required:true},
				'dob':{date: true},
				'gender':{required:true},
				'casteId':{required:true},
				'subCasteId':{required:true},
				'stateId':{required:true}
			},
			messages:{
				'name' : {required:'Name Can not be empty'},
				'phno':{
					required:'Phone number Can not be empty',
					digits: "Digits Only",
					minlength:'Phone number minimum 10 digits', 
					maxlength:'Phone number minimum 11 digits'
				},
				
				'referedRegid':{required:'Reference Id can not be empty'},
				'status':{required:'Please select Status'},
				'dob':{date: 'Please provide a valid date'},
				'gender':{required:'Gender required'},
				'casteId':{required:'Category required'},
				'subCasteId':{required:'Caste required'},
				'stateId':{required:'State required'}
			}
		});
		
		return rules;
	}
	
	function updateUserFormRules($form){
		var rules = $form.validate({
			ignore: ":hidden:not('input[type=text]')",
			
			rules:{
				'name' : {required:true},
				'phno':{ required:true, digits: true, minlength:10, maxlength:11 },
				'referedRegid':{required:true},
				'fatherName':{required:true},
				'status':{required:true},
				'dob':{date: true},
				'gender':{required: true},
				'casteId':{required:true},
				'subCasteId':{required:true},
				'address1':{required:true},
				'stateId':{required:true},
				'constituencyId':{required:true},
				'villageId':{required:true}
			},
			messages:{
				'name' : {required:'Name Can not be empty'},
				'phno':{
					required:'Phone number Can not be empty',
					digits: "Digits Only",
					minlength:'Phone number minimum 10 digits', 
					maxlength:'Phone number minimum 11 digits'
				},
				'fatherName':{required:'Relative Name can not be empty'},
				'referedRegid':{required:'Reference Id Can not be empty'},
				'status':{required:'Please select Status'},
				'dob':{date: 'Please provide a valid date'},
				'gender':{required:'Gender required'},
				'casteId':{required:'Category required'},
				'subCasteId':{required:'Caste required'},
				'address1':{required:'Address required'},
				'stateId':{required:'State required'},
				'constituencyId':{required:'Constituency name required'},
				'villageId':{required:'Village name required'}
			}
		});
		
		return rules;
	}
	
	reg.loginClick = function(){
		var formData = new FormData();
		formData.append('username',reg.login.username);
		formData.append('password',reg.login.password);
		
		$http({
		    method: 'POST',
		    url: 'user/login',
		    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		        var str = [];
		        for(var p in obj)
		        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		        return str.join("&");
		    },
		    data: {username: reg.login.username, password: reg.login.password}
		}).then(function (response) {
			if(response.statusText=="OK"){
				$rootScope.user = response.data.data.userDetails;
				//$state.go('users');
				$window.location.reload();
			}else{
				reg.login.errmsg = 'Invalid credntials.';
			}
				
		},
	    function error(response){
			reg.login.errmsg = response.data.message;
	    });
			
	}
	
	reg.mobile = {};
	reg.sendOTP = function (){
		
		var $regForm = $("#regForm");
		// Add Validations
		updateUserFormRules($regForm);
		if(!$regForm.valid())
			return;
				
		if(reg.model.phno&&reg.model.id){
			UtilService.postObject('user/rest/sendotp',reg.model).then(function(res){
				if(res && res.statusCode == 'OK'){
					reg.model = res.data.user;
					reg.mobile.otpmsg = 'OTP sent..!';
					reg.mobile.otpmsgcolor = {color:'#87b87f!important'};
					UtilService.messageToaster('success',reg.mobile.otpmsg)
				}else{
					reg.mobile.otpmsg = 'OTP sending failed try again..!';
					reg.mobile.otpmsgcolor = {color:'#b52c26!important;'};
					UtilService.messageToaster('error',reg.mobile.otpmsg)
				}
				
				
			});
		}else{
			reg.mobile.otpmsg = 'Inavlid details'
			UtilService.messageToaster('error',reg.mobile.otpmsg)
		}
		
	}
	
	reg.otpVerify = function(){
		
		if(reg.model.otp && reg.mobile.otp && (reg.model.otp == reg.mobile.otp) ){
			reg.model.otp = reg.mobile.otp;
			if(reg.model.constituencyId){
				angular.forEach(reg.staticData.constituencies,function(constituency){
					if(reg.model.constituencyId==constituency.id){
						reg.model.districtId = constituency.districtId
					}
				});
			}
			
			if(reg.model.mandalId){
				angular.forEach(reg.staticData.villages,function(village){
					if(reg.model.villageId==village.id){
						reg.model.mandalId = village.id;
					}
				});
			}
			
			UtilService.postObject('user/rest/verify_reg_otp',reg.model).then(function(res){
				if(res && res.statusCode == 'OK'){
					reg.model = res.data.user;
					reg.mobile.otpmsg = 'Verfication done.';
					reg.mobile.otpmsgcolor = {color:'#87b87f!important;'};
					UtilService.messageToaster('success',reg.mobile.otpmsg)
				}else{
					reg.mobile.otpmsg = 'Invalid OTP';
					reg.mobile.otpmsgcolor = {color:'#b52c26!important;'};
					UtilService.messageToaster('error',reg.mobile.otpmsg)
				}
			});
		}else{
			reg.mobile.otpmsg = 'Invalid OTP..!';
			reg.mobile.otpmsgcolor = {color:'#b52c26!important;'};
			UtilService.messageToaster('error',reg.mobile.otpmsg)
			
		}
	}
	
	reg.casteChange = function(){
		
		if(!reg.model.casteId)
			return;
		
		UtilService.getObject('user/rest/subCastes/'+reg.model.casteId).then(function(res){
			reg.staticData.subCastes = res;
		});
	}
	
	reg.stateChange = function(){
		
		if(!reg.model.stateId && reg.model.stateId.trim().length==0)
			return;
		
		UtilService.getObject('user/rest/constsbystate/'+reg.model.stateId).then(function(res){
			reg.staticData.constituencies = res;
		});
	}
	
	reg.constituencyChange = function(){
		
		if(!reg.model.constituencyId)
			return;
		
		UtilService.getObject('user/rest/villagesbyconstid/'+reg.model.constituencyId).then(function(res){
			reg.staticData.villages = res;
		});
	}
	
}

function MyReferenceMembersCtrl($scope, $rootScope, $state, UtilService, UserService) {
	
	var myRef = this;
	
	//load static data
	UtilService.getObject('user/rest/users/'+$rootScope.loggedUser.regid).then(function(response){
		myRef.users =response;
	});
	
	myRef.getStatusClass = function(status){
		return UserService.getStatusClass(status);
	}
	
}

function VerificatinCtrl($scope,$state, UtilService, UserService) {
	
	var veri = this;
	
	//load static data
	UtilService.getObject('user/rest/users').then(function(response){
		veri.users =response;
	});
	
	veri.editProfile =function(id){
		$state.go('edit-profile',{'id':id});
	}
	
	veri.getStatusClass = function(status){
		return UserService.getStatusClass(status);
	}
}

function ProfileCtrl($scope, UtilService, $stateParams){
	var profile = this;
	profile.id = $stateParams.id;
	profile.model = { id:profile.id};
	//load static data
	UtilService.getObject('user/rest/user/'+profile.id).then(function(response){
		profile.model = response;
	});
}

function EmployeeCtrl($scope, UtilService, UserService){
	var emp = this;
	emp.model = {};
	emp.employees = [];
	emp.roles = [];
	
	//load static data
	UtilService.getObject('user/rest/employees').then(function(response){
		emp.employees =response.employees;
		emp.roles =response.roles;
	});
	
	emp.editProfile =function(id){
		$state.go('edit-profile',{'id':id});
	}
	
	$("#regid").autocomplete({
       source: function( request, response ) {
        $.ajax( {
          url: 'user/rest/searchregid/'+request.term,
          success: function( data ) {
        	  for(var i=0; i<data.length;i++){
        		  val = data[i];
        		  val.value=val.regid;
        		  val.label=val.regid;
        	  }
            response( data );
          }
        } );
      },
      minLength: 3,
      select: function( event, ui ) {
    	  $scope.$apply(function () {
    		  emp.model = ui.item;  
    	  });
    	  
        //log( "Selected: " + ui.item.value + " aka " + ui.item.id );
      }
    });
	 
	emp.saveEmp = function(){
		var $form =$('#employeeForm');
		formRules($form);
		if(!$form.valid())
			return;
		if(!emp.model.regid || emp.model.regid.trim().length === 0){
			emp.msg='Please enter a valid Registration Id.';
			return;
		}
			
		if(!emp.model.id){
			emp.msg='Please enter a valid Registration Id.';
			return;
		}
		
		UtilService.postObject( 'user/rest/signup', emp.model ).then(function(response){
			emp.employees.push(response);
		});
	}
	
	function formRules($form){
		var rules = $form.validate({
			ignore: ":hidden:not('input[type=text]')",
			
			rules:{
				'regid' : {required:true},
				'phno':{ required:true },
				'name':{ required:true },
				'role':{ required:true }
			},
			messages:{
				'regid' : {required:'Valid Registartion Id'},
				'phno':{ required:'Can not be empty' },
				'name':{ required:'Can not be empty'},
				'role':{ required:'Please select employee role' }
			}
		});
		
		return rules;
	}
	emp.getStatusClass = function(status){
		return UserService.getStatusClass(status);
	}
}

function DashboardCtrl($scope,$state, UtilService,UserService) {
	
	var dashboard = this;
	
	//load static data
	UtilService.getObject('user/rest/users').then(function(response){
		dashboard.users =response;
	});
	
	dashboard.editProfile =function(id){
		$state.go('edit-profile',{'id':id});
	}
	dashboard.getStatusClass = function(status){
		return UserService.getStatusClass(status);
	}
}

// Ingest functions to controllers into module
var mod = angular.module('apsf');
mod.controller("MainCtrl", MainCtrl)
mod.controller("RegistrationCtrl",RegistrationCtrl)
mod.controller("EmployeeCtrl",EmployeeCtrl)
mod.controller("VerificatinCtrl",VerificatinCtrl)
mod.controller("DashboardCtrl",DashboardCtrl)
mod.controller("MyReferenceMembersCtrl",MyReferenceMembersCtrl)
mod.controller("ProfileCtrl",ProfileCtrl)


