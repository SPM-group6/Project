<template>
  <div>
    <common-layout>
      <img id="bg" src="../../assets/img/cover.png">
      <div style="display: flex">
        <!--<div style="flex-basis: 5%"></div>-->
        <!--<div class="left" style="flex-basis: 65%;display: flex">-->
        <!--  <div style="width:100%;align-items:center">-->
        <!--    <p>FrStaffHome</p>-->
        <!--    <h1>员 工 之 家</h1>-->
        <!--    <h5>员工之家&#45;&#45;员工的好助手</h5>-->
        <!--  </div>-->
        <!--</div>-->
        <div style="flex-basis: 55%"></div>
        <div class="box" style="flex-basis: 30%">
          <div class="login" >
            <a-form @submit="onSubmit" :form="form">
              <a-tabs :default-active-key="defaultTabKey" size="large" :tabBarStyle="{textAlign: 'center'}" style="padding: 0 2px;color: #bfbfbf">
                <a-tab-pane tab="人脸识别" key="1">
                  <a-row type="flex" justify="center">
                    <img src="../../assets/img/camera.png" height="200" width="200" @click="showModalAndOpenCamera()" style="cursor: pointer; margin-top: 50px"/>
                    <a-modal v-model="visible" title="智能识别" destroyOnClose :mask-closable="false">
                      <template slot="footer">
                        <a-button key="setImg" @click="setImage">
                          开始识别
                        </a-button>
                        <a-button key="back" @click="handleCancel">
                          返回
                        </a-button>
                      </template>
                      <!--                      摄像头组件-->
                      <div class="camera_outer">
                        <div v-if="showVideo">
                          <video id="videoCamera" :width="videoWidth" :height="videoHeight" autoplay></video>
                        </div>
                        <canvas  style="display:none;" id="canvasCamera" :width="videoWidth" :height="videoHeight" ></canvas>
                        <div v-if="showPicture" class="img_bg_camera">
                          <img :src="imgSrc" alt="" class="tx_img">
                        </div>
                      </div>
                    </a-modal>
                  </a-row>
                  <a-form-item style="color: white;text-align: center">
                    点击上方图标进行人脸识别
                  </a-form-item>
                </a-tab-pane>
                <a-tab-pane tab="账户密码登录" key="2" style="width:80%;margin-left: 10%;margin-top: 8px">
                  <a-alert type="error" :closable="true" v-if="error" :message="error" @close='onClose' showIcon style="margin-bottom: 24px" />
                  <a-form-item>
                    <a-input
                        size="large"
                        placeholder="请输入手机号码"
                        v-decorator="['phone', {rules: [{ required: true, message: '请输入手机号码'}]}]"
                        @blur="validatePhoneBlur"
                    >
                      <a-icon slot="prefix" type="user" />
                    </a-input>
                  </a-form-item>
                  <a-form-item>
                    <a-input
                        size="large"
                        placeholder="请输入密码"
                        autocomplete="on"
                        type="password"
                        v-decorator="['password', {rules: [{ required: true, message: '请输入密码', whitespace: true}]}]"
                    >
                      <a-icon slot="prefix" type="lock" />
                    </a-input>
                  </a-form-item>
                  <a-form-item prop="code">
                    <a-row :span="24">
                      <a-col :span="12">
                        <a-input
                            size="large"
                            placeholder="请输入验证码"
                            v-decorator="['validate', {rules: [{ required: true, message: '请输入验证码!' }]}]"
                        />
                      </a-col>
                      <a-col :span="12">
                        <div class="login-code" @click="refreshCode">
                          <!--验证码组件-->
                          <s-identify :identifyCode="identifyCode"></s-identify>
                        </div>
                      </a-col>
                    </a-row>
                  </a-form-item>
                  <!--<div>-->
                  <!--  <a-checkbox :checked="true" style="color: white">自动登录</a-checkbox>-->
                  <!--  <a style="float: right">忘记密码</a>-->
                  <!--</div>-->
                  <a-form-item>
                    <a-button :loading="logging" style="width: 60%;margin: 30px 20% 0px 20%;font-size: large;letter-spacing: 8px" size="large" htmlType="submit" type="primary">登录</a-button>
                  </a-form-item>
                </a-tab-pane>
              </a-tabs>
            </a-form>
          </div>
        </div>
        <div style="flex-basis: 15%"></div>
      </div>
    </common-layout>
  </div>
</template>

<script>
import CommonLayout from '@/layouts/CommonLayout'
import SIdentify from '@/components/sidentify/SIdentify'
import {login,faceLogin} from '@/services/user'
// import {setAuthorization} from '@/utils/request'
// import {loadRoutes} from '@/utils/routerUtil'
import {mapMutations} from 'vuex'

export default {
  name: 'Login',
  components: {CommonLayout, SIdentify},
  data() {
    return {
      defaultTabKey:"1",
      logging: false,
      error: '',
      form: this.$form.createForm(this),
      visible: false,
      identifyCodes: '1234567890',
      identifyCode: '',
      //摄像头组件所需参数
      videoWidth: 400,
      videoHeight: 300,
      imgSrc: '',
      thisCancas: null,
      thisContext: null,
      thisVideo: null,
      validating:false,
      images: {} //要传给后端的图片
    }
  },
  computed: {
    id(){
      return this.$store.state.employee.id
    },
    systemName() {
      return this.$store.state.setting.systemName
    },
    showPicture(){
      return this.imgSrc&&this.validating;
    },
    showVideo(){
      return !this.validating
    }
  },
  methods: {
    // ...mapMutations('account', ['setPermissions','setRoles','setUser']),
    ...mapMutations('employee', ['setId', 'setJobID', 'setDeptID', 'setPhone', 'setPassword', 'setUserName', 'setIsAdmin', 'setFaceURL',
      'setFacePath', 'setPortraitPath', 'setCardID', 'setAddress', 'setQqNum', 'setEmail', 'setSex', 'setParty',
      'setBirthday', 'setRace0','setEducation', 'setSpeciality', 'setCreateDate', 'setConfirmInf', 'setRemark', 'setIsDelete']),
    showModal() {
      this.visible = true;
    },
    //拍照界面返回
    handleCancel() {
      this.visible = false;
      this.stopNavigator();
    },
    onSubmit(e) {
      e.preventDefault()
      this.form.validateFields((err) => {
        if (!err) {
          this.logging = true
          if (this.form.getFieldValue('validate') !== this.identifyCode) {
            this.error = "验证码错误"
            this.form.setFieldsValue('password', '')
            console.log(this.form.getFieldValue("password"))
            console.log("密码已清空")
            this.$router.push({path: '/', query: {defaultTabKey: "2"}});
            this.$router.go(-1);
            this.logging = false
          } else {
            const phone = this.form.getFieldValue('phone')
            const password = this.form.getFieldValue('password')
            login(phone, password).then(this.afterLogin)
          }
        }
      })
    },
    afterLogin(res) {
      this.logging = false
      const loginRes = res.data
      if (loginRes.code === 0) {
        // const {user, permissions, roles} = loginRes.data
        // this.setUser(user)
        // this.setPermissions(permissions)
        // this.setRoles(roles)
        // // setAuthorization({token: loginRes.data.token, expireAt: new Date(loginRes.data.expireAt)})
        // // 获取路由配置
        this.initVuex(loginRes)
        this.$router.push('/home')
        this.$message.success("登录成功", 3)
        // getRoutesConfig().then(result => {
        //   const routesConfig = result.data.data
        //   loadRoutes(routesConfig)
        //
        // })
      } else if(loginRes.code === 1){
        this.$message.error("用户不存在",3)
      } else{
        this.$message.error("账号名或密码错误",3)

      }
    },
    randomNum(min, max) {
      console.log("randomNum")
      return Math.floor(Math.random() * (max - min) + min)
    },
    makeCode(o, l) {
      console.log("makeCode")
      for (let i = 0; i < l; i++) {
        this.identifyCode += o[
            this.randomNum(0, o.length)
            ]
      }
      console.log(this.identifyCode)
    },
    refreshCode() {
      console.log("refreshCode")
      this.identifyCode = ''
      this.makeCode(this.identifyCodes, 4)
    },
    validatePhoneBlur(e) {
      console.log("validatePhoneBlur()")
      const validatePhoneReg = /^1\d{10}$/
      if (e.target.value && !validatePhoneReg.test(e.target.value)) {
        const arr = [{
          message: '您输入的手机格式不正确!',
          field: 'phone',
        }]
        this.form.setFields({phone: {value: e.target.value, errors: arr}})
      }
    },
    onClose() {
      this.error = false
    },
    // 调用权限（打开摄像头功能）
    getCompetence() {
      var _this = this
      this.thisCancas = document.getElementById('canvasCamera')
      this.thisContext = this.thisCancas.getContext('2d')
      this.thisVideo = document.getElementById('videoCamera')
      // 旧版本浏览器可能根本不支持mediaDevices，我们首先设置一个空对象
      if (navigator.mediaDevices === undefined) {
        navigator.mediaDevices = {}
      }
      // 一些浏览器实现了部分mediaDevices，我们不能只分配一个对象
      // 使用getUserMedia，因为它会覆盖现有的属性。
      // 这里，如果缺少getUserMedia属性，就添加它。
      if (navigator.mediaDevices.getUserMedia === undefined) {
        navigator.mediaDevices.getUserMedia = function (constraints) {
          // 首先获取现存的getUserMedia(如果存在)
          var getUserMedia = navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.getUserMedia
          // 有些浏览器不支持，会返回错误信息
          // 保持接口一致
          if (!getUserMedia) {
            return Promise.reject(new Error('getUserMedia is not implemented in this browser'))
          }
          // 否则，使用Promise将调用包装到旧的navigator.getUserMedia
          return new Promise(function (resolve, reject) {
            getUserMedia.call(navigator, constraints, resolve, reject)
          })
        }
      }
      var constraints = {
        audio: false,
        video: {width: this.videoWidth, height: this.videoHeight, transform: 'scaleX(-1)'}
      }
      navigator.mediaDevices.getUserMedia(constraints).then(function (stream) {
        // 旧的浏览器可能没有srcObject
        if ('srcObject' in _this.thisVideo) {
          _this.thisVideo.srcObject = stream
        } else {
          // 避免在新的浏览器中使用它，因为它正在被弃用。
          _this.thisVideo.src = window.URL.createObjectURL(stream)
        }
        _this.thisVideo.onloadedmetadata = function () {
          _this.thisVideo.play()
        }
      }).catch(err => {
        console.log(err)
      })
    },
    showModalAndOpenCamera(){
      this.showModal()
      setTimeout(() =>{
        this.getCompetence()
      },300);
      console.log("摄像头已打开")
    },
//  绘制图片（拍照功能）
    setImage() {
      for(let i=0;i<2;i++){
        setTimeout(300)
        var _this = this
        // 点击，canvas画图
        _this.thisContext.drawImage(_this.thisVideo, 0, 0, 200, 150)
        // _this.thisContext.drawImage(_this.thisVideo, 0, 0, _this.videoWidth, _this.videoHeight)
        // 获取图片base64链接
        var image = this.thisCancas.toDataURL('image/png')
        _this.imgSrc = image
        this.$emit('refreshDataList', this.imgSrc)

        console.log("第"+(i+1)+"张照片已拍摄")
        console.log(this.imgSrc)
        this.images[i]=this.imgSrc
        console.log(this.images)
      }
      faceLogin(this.images).then(this.afterFaceLogin)
    },

    afterFaceLogin(res){
      let loginRes = res.data
      if(loginRes.code === 0){
        this.validating=true
        this.stopNavigator() //关闭摄像机

        this.initVuex(loginRes)
        // this.setUser("user")
        // this.setPermissions([{id: 'queryForm', operation: ['add', 'edit']}])
        // this.setRoles([{id: '13500000000', operation: ['add', 'edit', 'delete']}])
        // setAuthorization({token: res.data.token, expireAt: new Date(res.data.expireAt)})
        // 获取路由配置

        this.$router.push('/home')
        this.$message.info('登录成功')
        // getRoutesConfig().then(result => {
        //   const routesConfig = result.data.data
        //   loadRoutes(routesConfig)
        // })
      }
      else{
        this.$message.error('验证失败,请重新验证')
      }
    },
    //初始化vuex全局
    initVuex(res){
      let data=res.data
      this.setId(data.id)
      this.setJobID(data.jobID)
      this.setDeptID(data.deptID)
      this.setPhone(data.phone)
      this.setPassword(data.password)
      this.setUserName(data.userName)
      this.setIsAdmin(data.isAdmin)
      this.setFaceURL(data.faceURL)
      this.setFacePath(data.facePath)
      this.setPortraitPath(data.portraitPath)
      this.setCardID(data.cardID)
      this.setAddress(data.address)
      this.setQqNum(data.qqNum)
      this.setEmail(data.email)
      this.setSex(data.sex)
      this.setParty(data.party)
      this.setBirthday(data.birthday)
      this.setRace0(data.race0)
      this.setEducation(data.education)
      this.setSpeciality(data.speciality)
      this.setCreateDate(data.createDate)
      this.setConfirmInf(data.confirmInf)
      this.setRemark(data.remark)
      this.setIsDelete(data.isDelete)
    },
// 关闭摄像头

    stopNavigator() {
      this.thisVideo.srcObject.getTracks()[0].stop()
    },

    created() {
      console.log("created")
      this.refreshCode()
    }
  }
}
</script>

<style lang="less" scoped>
.left{
  text-align: center;
  h1 {
    font-size: 100px;
    font-family: 'Papyrus';
    font-weight: 600;
    padding-bottom: 44px;
    margin: 30px 0 0;
    letter-spacing: -4.5px;
    line-height: 90px
  }
  p {
    font-family: 'Papyrus';
    letter-spacing: 7.1px;
    font-size: 30px;
    line-height: 23px;
    margin-bottom: 9px
  }
  h5 {
    font-family: 'Papyrus';
    font-size: 23px;
    letter-spacing: .7px;
    line-height: 23px;
    margin: 5px 0 0
  }
}


.desc {
  font-size: 14px;
  color: @text-color-second;
  margin-top: 12px;
  margin-bottom: 40px;
}

.box{
  background:rgba(0,0,0,0);
  box-sizing:border-box;
  border-radius: 10px;/*登录窗口边角圆滑*/
  width: 368px;
  margin: 0 auto;
  @media screen and (max-width: 576px) {
    width: 95%;
  }
  @media screen and (max-width: 320px) {
    .captcha-button{
      font-size: 14px;
    }
  }
}
.login{
  .icon {
    font-size: 24px;
    color: @text-color-second;
    margin-left: 16px;
    vertical-align: middle;
    cursor: pointer;
    transition: color 0.3s;

    &:hover {
      color: @primary-color;
    }
  }
}

#bg{
  z-index: -999;
  position: fixed;
  top: -5%;
  left: 0%;
  width: 100%;
  height:110%;
  background-size: 100% 100%;
}



</style>
