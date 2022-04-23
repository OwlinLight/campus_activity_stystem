# campus_activity_stystem

我们的项目已经基本具备了基础的所有功能。包括学生报名参与，签到签退。活动举办者申报活动，举办活动的功能。还有审核者审批活动，保卫处检验活动资质的功能。结合我们独到的基于地理位置的推荐系统，学生很难由于信息不足，错失了自己本来可以获得的学分。

除此之外，系统与学生校园生活的方方面面深度融合，破除了传统活动申报系统，第二课堂积分软件的僵硬死板，杂乱无章的特点。能够根据使用者报名活动的习惯进行智能推荐。同时，可以基于使用者的地理位置，实时推荐距离相近的活动，减少了可能的时间浪费，提高了参加活动的效率。除此之外，管理员可以看到活动的实时热力图，以及一段时间之内的时间统计，可以生成相关分析，进行具体可操作的调整建议。

![用例图](.\pic\image-20220422212922258.png)

![E-R](.\pic\image-20220422213001833.png)

## 6.1普通用户系统实现

本系统普通用户拥有的功能有：登录、注册、创建活动、报名审核已通过的活动、查看自己已报名的活动、查看自己创建的活动、修改自己的密码

1.  **登录功能**

①用户登录界面为[http://localhost:3000/dva-boot-admin\#/dva-boot-admin/sign/login](http://localhost:3000/dva-boot-admin#/dva-boot-admin/sign/login)，如图所示。

![Graphical user interface, website Description automatically generated](media/82b98d3249f03455900e4b7c802ead4f.png)

图6-1

②用户输入自己的学号和密码后，调用/activity/LoginUser中的loginUser（）方法验证自己身份信息，如图所示，进入系统完成登陆。

![Graphical user interface, text, application Description automatically generated](media/33d4e0f05fb7578a3aca545f22cfef48.png)

图6-2

关键代码：

![Graphical user interface, text, application, email Description automatically generated](media/8b6638f44ca6a1e1d4243be749a5e300.png)

③退出系统

点击“退出系统”，清除浏览器存储的store，跳转到登录页面。

![Graphical user interface, text, application, Word Description automatically generated](media/7d0658423640c09d0d64590ca4d3a22a.png)

1.  **注册功能**
    1.  用户注册界面为[http://localhost:3000/dva-boot-admin\#/dva-boot-admin/sign/login](http://localhost:3000/dva-boot-admin#/dva-boot-admin/sign/login)，如图所示。

![Graphical user interface, website Description automatically generated](media/b06b7fb7bca258d061a3638c011ca288.png)

1.  用户在获取验证码时，会调用getAuthcode（）方法，获取验证码。当验证码输入错误时，如图所示。

    ![Graphical user interface, website Description automatically generated](media/cbb91d9a00877963ae33482b2d3beed7.png)

    关键代码：

    ![Text Description automatically generated](media/007c659c47a349bf14d6b69ed4ba2fed.png)

    ![Graphical user interface, text Description automatically generated](media/b5bc18bb18ba3bf7f9fbc4f04fb3e93e.png)

    1.  用户输入自己信息，调用/activity/createUser中的createUser（）方法。创建成功时，会跳转到注册成功页面

![Graphical user interface, text, application, chat or text message Description automatically generated](media/2ae46a994f15f4541cca233f3c4b3b5b.png)

关键代码：

![Graphical user interface, text, application Description automatically generated](media/0f8fdca7565847fe211a9d7835289c23.png)

1.  **创建活动功能**
    1.  用户可在左侧导航处点击创建活动进入创建活动页面，如图所示。

![Graphical user interface, application Description automatically generated](media/63e89843256c35dc211ed508e8bc0e09.png)

1.  用户可通过调用/minio/upload方法上传图片至服务器中，如下图所示。

![Graphical user interface, application, Word Description automatically generated](media/968c69caec57a4081a52fd289b7b4db1.png)

可在后台查看上传的图片信息。

![](media/e2a630f6c09cee5f5525a7a2b115ba6f.png)

关键代码：

![Text Description automatically generated](media/a7752771bf768daed69026d0424c0f88.png)

1.  用户通过百度Bmap提供的JavaScript库来查询地点信息，使得活动地点信息的录入准确无误。

![Graphical user interface, text, application, email Description automatically generated](media/e9a7cb3b9c6be161d7fbe4d616fd7cd3.png)

1.  用户通过调用/activity/create中的createActivity（）方法创建出相应的活动信息，创建成功信息如图所示。

![Graphical user interface, application Description automatically generated](media/eada218de4afe78cfd62b1217a4bd086.png)

关键代码：

![Graphical user interface, text, application Description automatically generated](media/52e00b5dddcf37a9ba8204553f9a6790.png)

1.  **报名审核通过的活动**
    1.  用户可在左侧导航处点击报名活动进入报名活动页面，如图所示。

![Graphical user interface, application Description automatically generated](media/8a85e8fa5d078f16a9cdd333a63460eb.png)

关键代码：

![A picture containing text Description automatically generated](media/6b5615d2be128019855248766dade32b.png)

1.  用户可在操作栏点击链接可跳转至用户详情界面，如图所示。

![Graphical user interface, text, application Description automatically generated](media/ce6b56f174fb50d861a9f0e21e5eee2c.png)

关键代码：

![Text Description automatically generated](media/3242b410adce435ff81e7c52d328f524.png)

1.  用户通过扫描二维码跳转到签到界面。需填写相应的学号和姓名信息完成该活动的签到，且满足签到时间在活动开始前后30分钟以及活动未过期。

![Graphical user interface Description automatically generated](media/bd3e29846df5a786315dc83ab26298e9.png)

关键代码：

![Graphical user interface, text, application, chat or text message Description automatically generated](media/4614de303390f9888872856153d7f504.png)

![Graphical user interface, text, application Description automatically generated](media/85262a6873946242bdb02a5df5dd7d88.png)

1.  用户可通过点击活动概述模块可查看当前活动的详细信息。

    ![Graphical user interface, application Description automatically generated](media/0db08bdefe951dff7c7a6eeb5e84a6cd.png)

    1.  用户可通过点击活动进度模块可查看当前活动的实时信息。

![Graphical user interface, text, application Description automatically generated](media/2a0f34194d256b6656cf042e1e1b1a0c.png)

1.  用户可通过点击活动评论模块可查看当前参与者对该活动的相关评论及相应打分。

![Graphical user interface, text, application, email Description automatically generated](media/ad5c749837c5f2b7407444d6abd7533f.png)

1.  用户可通过点击✔图标来报名自己感兴趣的活动，并进一步通过弹出的模块框确认。

    ![Graphical user interface, text, application, email Description automatically generated](media/68a45bfeb209147008824ccc7eb49734.png)

    关键代码：

    ![Graphical user interface, text, application Description automatically generated](media/8a215d75bc2428611c18298eb1729aa3.png)

    1.  用户可在右上角根据关键字模糊查询相应活动信息。通过调用/activity/askBykeywords中的askBykeywords（）方法返回对应活动信息。

![Graphical user interface, application Description automatically generated](media/06c19f842f221965c4dc82c515159691.png)

关键代码：

![Text Description automatically generated with low confidence](media/0bcb3dd5e806ded2cfe5a489a662b415.png)

1.  **查看已报名活动**
    1.  用户可在左侧导航处点击已报名活动进入已报名活动页面，如图所示。

        ![Graphical user interface, text, application Description automatically generated](media/b1ec1e823b5643c4620abc6941bcddd2.png)

    2.  通过调用/activity/getUserActivity中的getUserActivity（）方法，传入用户学号，可获取用户已报名的相关活动。

        关键代码：

        ![Text Description automatically generated](media/3a308c30b1bca97f138c5cf9fe8ab8e0.png)

2.  **查看已创建的活动**
    1.  用户可在左侧导航处点击已创建活动进入已创建活动页面，如图所示。

        ![Graphical user interface, text, application, email Description automatically generated](media/ed04db0570c18043c179651a6f3d8d1f.png)

    2.  通过调用/activity/director中的geDirectorrActivity（）方法，传入用户学号，可获取用户已创建的相关活动。

        关键代码：

        ![Text Description automatically generated](media/7ed30a41dd91ee4b0db40a6441e2fc3b.png)

## 6.2管理员系统实现

管理员是系统中很重要的一个角色,我们这个系统需要在用户报名活动之初就要设立为这个用户设立一个管理员,这个管理员主要负责进行审核用户提交上来的活动，并予以通过、不通过的处理。同时管理员还能修改活动的相关信息，包括活动学院和负责人等。

1.  **管理员的登录入口和注册入口和普通用户一样。我们可以通过登录者的role字段来区分身份。数据库属性如下。**

    ![Graphical user interface Description automatically generated](media/baa7815e4db7aff4a84c70fdf58d9fb7.png)

2.  **待审批活动**
    1.  管理员可通过点击左侧导航栏活动列表下的待审批活动查看自己将要审核的活动，从中调用/activity/listFailed中的listActivityFailed（）方法。如下图所示。

![Graphical user interface, text, application, email Description automatically generated](media/e74ae03aceb10e94afafd7bd6f0e9047.png)

关键代码：

![Text Description automatically generated with low confidence](media/c91365991b0edd4514a23858387a489a.png)

1.  管理员可点击修改按钮修改活动的相关信息。如下图所示。

![Graphical user interface, application Description automatically generated](media/7f8f717d97d1c4980fcb61e53a803246.png)

关键代码：

![Graphical user interface, text, application Description automatically generated](media/dca6ff55ceacde8678081758a792e780.png)

1.  管理员可点击通过按钮或不通过按钮选择是否通过该活动。如下图所示。

![Graphical user interface, application, Teams Description automatically generated](media/269f64ec2794ba7d018224702ccb620d.png)

关键代码：

![Graphical user interface, text, application Description automatically generated with medium confidence](media/cbfe4dcb3454ec4f6298ca25baa5ea4e.png)

1.  **已通过活动**
    1.  管理员可通过点击左侧导航栏活动列表下的已通过活动查看自己已经通过的活动，从中调用/activity/listPassed中的listActivityPassed（）方法。如下图所示。

![Graphical user interface, application Description automatically generated](media/3314679b5bcf4bb3fe52a8e6214f4b23.png)

关键代码：

![Text Description automatically generated with medium confidence](media/5c7cc62460ea25930cccad62c89d0572.png)

1.  管理员可点击修改按钮修改活动的相关信息。如下图所示。

![Graphical user interface, application Description automatically generated](media/7f8f717d97d1c4980fcb61e53a803246.png)

关键代码：

![Graphical user interface, text, application Description automatically generated](media/dca6ff55ceacde8678081758a792e780.png)

1.  管理员可在操作栏点击链接可跳转至活动详情界面，如图所示。

![Graphical user interface, text, application Description automatically generated](media/ce6b56f174fb50d861a9f0e21e5eee2c.png)

关键代码：

![Text Description automatically generated](media/3242b410adce435ff81e7c52d328f524.png)

## 6.3功能测试

1.  **测试用户或管理员登录时学号密码不能为空。**

![Graphical user interface, website Description automatically generated](media/f3e78906a3e78a20f74a46ae18c3be13.png)

1.  **登陆成功页面。**

    ![Graphical user interface, text, application Description automatically generated](media/872bc9b8433ab65999198884a50e6b9f.png)

2.  **修改活动的承办学院。当要修改的学院在数据库的表项中时。将升旗仪式的学院修改为人文学院**

    ![Graphical user interface, text, application Description automatically generated](media/e65440568138ed36a2a7d553ef370abb.png)

    ![Graphical user interface, table Description automatically generated](media/e52e78f71b0d81e65a736f698bc2f1be.png)

3.  **修改活动的承办学院。当要修改的学院不存在于数据库的表项中时。将篮球比赛的学院修改为艺术学院。**

    ![Graphical user interface, application Description automatically generated](media/a16ed4e5cef8fd412e3b402276da6dd9.png)

4.  **修改活动的负责人。当要修改的负责人在数据库的表项中时。将篮球比赛的负责人修改为徐伟峰。**

    ![Graphical user interface, application Description automatically generated](media/bad49ea0ee24c464e6faa60f0fb8c705.png)

    ![Graphical user interface, application Description automatically generated](media/2798b184f94d5fcff1dd84b00d472893.png)

5.  **修改活动的负责人。当要修改的负责人不存在于数据库的表项中时。将篮球比赛的负责人修改为赵六。**

    ![Graphical user interface, application Description automatically generated](media/057b22348c24181815ca6488bd7948a2.png)

6.  **当用户注册，填写错误的验证码时。**

    ![Graphical user interface, website Description automatically generated](media/d2fe6fab5f9d02022265e1ef2f94f8c0.png)

7.  **用户输入正确的验证码**

    ![Graphical user interface, text, application, chat or text message Description automatically generated](media/f66061989b66ce75acf120ce484256f3.png)

8.  **用户在填写注册信息的某几项为空时。**

    ![Graphical user interface, website Description automatically generated](media/a6a18d3c26ae11950bbd44346fd41526.png)

9.  **当前时间不在活动签到的前后三十分钟内，而用户签到时。**

    ![Graphical user interface, application, Word Description automatically generated](media/bfa39ce51e4319294991f8cec3820e08.png)

10. **该活动未过期且当前时间在活动开始的前后三十分钟内。**

    ![Graphical user interface, application, Word Description automatically generated](media/f710ffae54eb540ece2d0f731dab77cd.png)

11. **用户在关键字搜索时不输入任何数据，会展示活动所有内容**

    ![Graphical user interface, table Description automatically generated](media/490341f04c7a1bf374eb37dba2d23516.png)

12. **用户在关键字搜索时检索错误信息，会显示暂无数据的信息**

    ![Graphical user interface, text, application, email Description automatically generated](media/3c1f82833914d46b9cc732d7372f920f.png)

![数据流图](.\pic\image-20220422213051353.png)
