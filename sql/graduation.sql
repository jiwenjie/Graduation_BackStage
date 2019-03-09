/*
Navicat MySQL Data Transfer

Source Server         : Web
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : graduation

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2019-03-08 17:15:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(255) DEFAULT NULL,
  `createtime` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of feedback
-- ----------------------------
INSERT INTO `feedback` VALUES ('1', '1250', '2019-3-1 11:55', 'ackjscsasa');
INSERT INTO `feedback` VALUES ('2', '1250', '2019-03-01 12:27:49', '啊是擦上按山粗');
INSERT INTO `feedback` VALUES ('3', '1250', '2019-03-03 20:38:40', 'ffgjg');
INSERT INTO `feedback` VALUES ('4', '1551770762192', '2019-03-05 15:33:35', '小女女vncv');

-- ----------------------------
-- Table structure for phoneuser
-- ----------------------------
DROP TABLE IF EXISTS `phoneuser`;
CREATE TABLE `phoneuser` (
  `userid` varchar(255) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `userphone` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `continuesigndays` int(11) DEFAULT NULL COMMENT '表示该用户连续签到多少天',
  `signintoday` tinyint(4) DEFAULT NULL,
  `signintime` varchar(255) DEFAULT NULL,
  `signintotaldays` int(11) DEFAULT NULL COMMENT '表示该用户一共签到多少天',
  `signout` tinyint(4) DEFAULT NULL,
  `logintime` varchar(255) DEFAULT NULL,
  `logouttime` varchar(255) DEFAULT NULL,
  `totaltime` varchar(255) DEFAULT NULL,
  `collectioncount` int(11) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像的存储地址',
  `bgimageurl` varchar(255) DEFAULT NULL,
  `profile` varchar(255) DEFAULT NULL COMMENT '个人简介部分',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of phoneuser
-- ----------------------------
INSERT INTO `phoneuser` VALUES ('1250', 'admin爱吃的爱的成都', '18755104612', 'ZTEwYWRjMzk0OWJhNTlhYmJlNTZlMDU3ZjIwZjg4M2U=\r\n', '3', '0', '2019-03-03 20:38:22', '3', null, null, null, null, null, 'upload/avatar/1551426544928.jpg', null, '大厂啊擦擦的的尝试多次供电公司');
INSERT INTO `phoneuser` VALUES ('1549765424568', 'admined', '15698741236', 'ZTEwYWRjMzk0OWJhNTlhYmJlNTZlMDU3ZjIwZjg4M2U=\r\n', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `phoneuser` VALUES ('1550060393002', null, '187 5510 4612', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `phoneuser` VALUES ('1550061852311', 'JackSon', '18755104613', 'ZTEwYWRjMzk0OWJhNTlhYmJlNTZlMDU3ZjIwZjg4M2U', '1', '0', '2019-02-13 21:00:26', '1', '0', null, null, null, null, null, null, null);
INSERT INTO `phoneuser` VALUES ('1550113004014', 'Mon并于不久后', '18755104614', 'ZTEwYWRjMzk0OWJhNTlhYmJlNTZlMDU3ZjIwZjg4M2U=\r\n', '2', '0', '2019-02-18 13:18:15', '2', '0', null, null, null, '1', 'upload/avatar/1550470045371.jpg', null, '士大并不恐怖DVD是v');
INSERT INTO `phoneuser` VALUES ('1550810951141', 'JackSoYYY', '18755104616', 'MjVkNTVhZDI4M2FhNDAwYWY0NjRjNzZkNzEzYzA3YWQ=\r\n', '1', '1', '2019-02-22 12:53:34', '1', '0', null, null, null, null, 'upload/avatar/1550811199505.jpg', null, '不擦卡巴厨师框架dcsdcsdSSS');
INSERT INTO `phoneuser` VALUES ('1550831692974', 'Msndcddcd', '18755104618', 'MjVkNTVhZDI4M2FhNDAwYWY0NjRjNzZkNzEzYzA3YWQ=\r\n', '1', '1', '2019-02-22 18:38:45', '1', '0', null, null, null, null, 'upload/avatar/1550831908069.jpg', null, 'scascascas');
INSERT INTO `phoneuser` VALUES ('1550832944017', 'JackMomasas', '18755104646', 'MjVkNTVhZDI4M2FhNDAwYWY0NjRjNzZkNzEzYzA3YWQ=\r\n', '1', '1', '2019-02-22 18:59:26', '1', '0', null, null, null, null, 'upload/avatar/1550833151611.jpg', null, 'ascascdcsdcdsasasc');
INSERT INTO `phoneuser` VALUES ('1550833783376', 'Uiok', '18755104678', 'MjVmOWU3OTQzMjNiNDUzODg1ZjUxODFmMWI2MjRkMGI=\r\n', null, null, null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `phoneuser` VALUES ('1550833972775', 'Yuiksdd', '18755104672', 'MjVkNTVhZDI4M2FhNDAwYWY0NjRjNzZkNzEzYzA3YWQ=\r\n', '1', '1', '2019-02-22 19:19:13', '1', '0', null, null, null, null, 'upload/avatar/1550834341353.jpg', null, 'dcdscsdcsdcsdc');
INSERT INTO `phoneuser` VALUES ('1551179680394', '咯嗯啦', '18851025102', 'MjVkNTVhZDI4M2FhNDAwYWY0NjRjNzZkNzEzYzA3YWQ=\r\n', '2', '1', '2019-03-01 15:56:39', '2', '0', null, null, null, null, 'upload/avatar/1551179945146.jpg', null, '咯哦哦看');
INSERT INTO `phoneuser` VALUES ('1551427039043', null, '13921400723', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `phoneuser` VALUES ('1551770762192', '那就富士康', '18755104619', 'ZTEwYWRjMzk0OWJhNTlhYmJlNTZlMDU3ZjIwZjg4M2U=\r\n', '1', '1', '2019-03-05 15:36:31', '1', '0', null, null, null, null, 'upload/avatar/1551771364763.jpg', null, '阿萨苏打水v但是va阿萨苏v');

-- ----------------------------
-- Table structure for todo
-- ----------------------------
DROP TABLE IF EXISTS `todo`;
CREATE TABLE `todo` (
  `todoid` varchar(255) NOT NULL,
  `userid` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `complete` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`todoid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of todo
-- ----------------------------
INSERT INTO `todo` VALUES ('1101773', '1550810951141', 'sacasc', 'ascascascsa', '2019-02-22 12:52:17', '1');
INSERT INTO `todo` VALUES ('1101817', '1550832944017', 'acscascas', 'cdcsvfdvf', '2019-02-22 18:58:33', '1');
INSERT INTO `todo` VALUES ('151', '1250', '爱吃的', '承受的程度', '2019-03-01 11:45:30', '1');
INSERT INTO `todo` VALUES ('1651102', '1550113004014', 'nscknalskcnlas', 'ajslkcaslkcaslkcasklcmasclkascas', '2019-02-16 19:39:26', '1');
INSERT INTO `todo` VALUES ('1652584', '1550810951141', 'sacs', 'ascasc', '2019-02-22 12:52:41', '0');
INSERT INTO `todo` VALUES ('1652650', '1550832944017', 'vfdvdfvdfv', 'vdsvsdvdsvds', '2019-02-22 18:58:48', '0');
INSERT INTO `todo` VALUES ('1801', '1550113004014', '测试', '测试测试测试测试i', '2019-02-16 18:20:18', '1');
INSERT INTO `todo` VALUES ('2203483', '1250', 'ascacdscd', 'vfvdfvfd', '2019-02-22 19:07:33', '1');
INSERT INTO `todo` VALUES ('2204867', '1551179680394', '李敏', '敏敏', '2019-02-26 19:19:52', '0');
INSERT INTO `todo` VALUES ('2204871', '1551179680394', '米诺', '就认识', '2019-02-26 19:20:26', '0');
INSERT INTO `todo` VALUES ('2351', '1550113004014', 'csnaslck', 'snacnaskjcnaskjcascascascasc', '2019-02-16 19:33:44', '0');
INSERT INTO `todo` VALUES ('2754306', '1550831692974', 'hjdcln', 'dncklsndlcksdcds', '2019-02-22 18:37:59', '1');
INSERT INTO `todo` VALUES ('2754321', '1550833972775', 'vdssdv', 'sdcscascascxz', '2019-02-22 19:19:36', '1');
INSERT INTO `todo` VALUES ('2901', '1550113004014', '就爱看时间', '开始v看v的苏联考察角度来说', '2019-02-16 18:52:04', '1');
INSERT INTO `todo` VALUES ('3302053', '1550113004014', 'bjkbknlknkl', 'kbjbj, m ,mn kjbjkbkj', '2019-02-16 19:48:46', '0');
INSERT INTO `todo` VALUES ('3305137', '1550831692974', 'ceshi', 'cnkeclkcaa', '2019-02-22 18:37:51', '0');
INSERT INTO `todo` VALUES ('3310777', '1551770762192', '德国刑法典', 'v需重新编写出版', '2019-03-05 15:33:13', '0');
INSERT INTO `todo` VALUES ('3451', '1550113004014', 'sdsddssd', 'efsefsdsdvd', '2019-02-16 19:23:36', '1');
INSERT INTO `todo` VALUES ('3855989', '1550833972775', 'dscsdcsd', 'cdscsdcfcfv', '2019-02-22 19:19:30', '0');
INSERT INTO `todo` VALUES ('550962', '1550810951141', 'ascsacsacsa', 'dcdcdcdcdcaczxc', '2019-02-22 12:52:27', '0');
INSERT INTO `todo` VALUES ('550984', '1250', 'sdfsdsd', 'vdvxvxc', '2019-02-22 19:07:48', '0');
INSERT INTO `todo` VALUES ('701', '1550113004014', '就爱看时间', '开始v看v的苏联考察角度来说', '2019-02-16 18:52:23', '1');

-- ----------------------------
-- Table structure for user_article
-- ----------------------------
DROP TABLE IF EXISTS `user_article`;
CREATE TABLE `user_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(255) NOT NULL,
  `articleid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_article
-- ----------------------------
INSERT INTO `user_article` VALUES ('1', '1550113004014', '7775');
INSERT INTO `user_article` VALUES ('2', '1550113004014', '7470');
INSERT INTO `user_article` VALUES ('3', '1550113004014', '7419');
INSERT INTO `user_article` VALUES ('4', '1550113004014', '7473');
INSERT INTO `user_article` VALUES ('5', '1550113004014', '7441');
INSERT INTO `user_article` VALUES ('7', '1550113004014', '7724');
INSERT INTO `user_article` VALUES ('8', '1550113004014', '7555');
INSERT INTO `user_article` VALUES ('9', '1550113004014', '7384');
INSERT INTO `user_article` VALUES ('10', '1550113004014', '7892');
INSERT INTO `user_article` VALUES ('11', '1550113004014', '7936');
INSERT INTO `user_article` VALUES ('13', '1550113004014', '3077');
INSERT INTO `user_article` VALUES ('14', '1550113004014', '1766');
INSERT INTO `user_article` VALUES ('15', '1550113004014', '7925');
INSERT INTO `user_article` VALUES ('16', '1550113004014', '7917');
INSERT INTO `user_article` VALUES ('17', '1550113004014', '7912');
INSERT INTO `user_article` VALUES ('20', '1550113004014', '7688');
INSERT INTO `user_article` VALUES ('21', '1550113004014', '7672');
INSERT INTO `user_article` VALUES ('22', '1550113004014', '7735');
INSERT INTO `user_article` VALUES ('23', '1550113004014', '7793');
INSERT INTO `user_article` VALUES ('24', '1550113004014', '3330');
INSERT INTO `user_article` VALUES ('25', '1550810951141', '7892');
INSERT INTO `user_article` VALUES ('26', '1550810951141', '7945');
INSERT INTO `user_article` VALUES ('27', '1550810951141', '7957');
INSERT INTO `user_article` VALUES ('28', '1550810951141', '7917');
INSERT INTO `user_article` VALUES ('29', '1550810951141', '7913');
INSERT INTO `user_article` VALUES ('30', '1550810951141', '7958');
INSERT INTO `user_article` VALUES ('31', '1550810951141', '3372');
INSERT INTO `user_article` VALUES ('32', '1550810951141', '3108');
INSERT INTO `user_article` VALUES ('33', '1550810951141', '7821');
INSERT INTO `user_article` VALUES ('34', '1550831692974', '7892');
INSERT INTO `user_article` VALUES ('35', '1550831692974', '7775');
INSERT INTO `user_article` VALUES ('36', '1550831692974', '7958');
INSERT INTO `user_article` VALUES ('37', '1550831692974', '7945');
INSERT INTO `user_article` VALUES ('38', '1550831692974', '7822');
INSERT INTO `user_article` VALUES ('39', '1550831692974', '7821');
INSERT INTO `user_article` VALUES ('40', '1550832944017', '7892');
INSERT INTO `user_article` VALUES ('41', '1550832944017', '7775');
INSERT INTO `user_article` VALUES ('42', '1550832944017', '7553');
INSERT INTO `user_article` VALUES ('43', '1550832944017', '7972');
INSERT INTO `user_article` VALUES ('44', '1550832944017', '7957');
INSERT INTO `user_article` VALUES ('45', '1550832944017', '7536');
INSERT INTO `user_article` VALUES ('46', '1550832944017', '7528');
INSERT INTO `user_article` VALUES ('47', '1550832944017', '1472');
INSERT INTO `user_article` VALUES ('48', '1550832944017', '180');
INSERT INTO `user_article` VALUES ('49', '1550832944017', '3327');
INSERT INTO `user_article` VALUES ('50', '1550833783376', '7892');
INSERT INTO `user_article` VALUES ('51', '1550833783376', '7775');
INSERT INTO `user_article` VALUES ('52', '1550833783376', '7722');
INSERT INTO `user_article` VALUES ('53', '1550833972775', '7892');
INSERT INTO `user_article` VALUES ('54', '1550833972775', '7775');
INSERT INTO `user_article` VALUES ('55', '1550833972775', '7694');
INSERT INTO `user_article` VALUES ('56', '1550833972775', '7641');
INSERT INTO `user_article` VALUES ('57', '1550833972775', '7553');
INSERT INTO `user_article` VALUES ('58', '1550833972775', '3632');
INSERT INTO `user_article` VALUES ('59', '1550833972775', '7957');
INSERT INTO `user_article` VALUES ('60', '1550833972775', '7315');
INSERT INTO `user_article` VALUES ('61', '1550833972775', '7149');
INSERT INTO `user_article` VALUES ('62', '1550833972775', '7784');
INSERT INTO `user_article` VALUES ('63', '1550833972775', '3205');
INSERT INTO `user_article` VALUES ('64', '1550833972775', '7535');
INSERT INTO `user_article` VALUES ('65', '1550833972775', '7424');
INSERT INTO `user_article` VALUES ('66', '1250', '7724');
INSERT INTO `user_article` VALUES ('67', '1250', '7913');
INSERT INTO `user_article` VALUES ('68', '1250', '7823');
INSERT INTO `user_article` VALUES ('69', '1551179680394', '7892');
INSERT INTO `user_article` VALUES ('70', '1551179680394', '7728');
INSERT INTO `user_article` VALUES ('71', '1551179680394', '7641');
INSERT INTO `user_article` VALUES ('72', '1551179680394', '2874');
INSERT INTO `user_article` VALUES ('73', '1551179680394', '1810');
INSERT INTO `user_article` VALUES ('74', '1551179680394', '7962');
INSERT INTO `user_article` VALUES ('75', '1551179680394', '7920');
INSERT INTO `user_article` VALUES ('76', '1250', '7892');
INSERT INTO `user_article` VALUES ('77', '1250', '7641');
INSERT INTO `user_article` VALUES ('78', '1250', '7958');
INSERT INTO `user_article` VALUES ('79', '1551179680394', '7975');
INSERT INTO `user_article` VALUES ('80', '1250', '7550');
INSERT INTO `user_article` VALUES ('81', '1551770762192', '7910');
INSERT INTO `user_article` VALUES ('82', '1551770762192', '7834');

-- ----------------------------
-- Table structure for wanandroid
-- ----------------------------
DROP TABLE IF EXISTS `wanandroid`;
CREATE TABLE `wanandroid` (
  `apklink` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `chapterid` int(11) DEFAULT NULL,
  `chaptername` varchar(255) DEFAULT NULL,
  `collect` tinyint(4) DEFAULT NULL,
  `courseid` int(11) DEFAULT NULL,
  `descs` varchar(255) DEFAULT NULL,
  `envelopepic` varchar(255) DEFAULT NULL,
  `fresh` tinyint(4) DEFAULT NULL,
  `id` int(11) NOT NULL,
  `link` varchar(255) DEFAULT NULL,
  `nicedate` varchar(255) DEFAULT NULL,
  `origin` varchar(255) DEFAULT NULL,
  `projectlink` varchar(255) DEFAULT NULL,
  `publishtime` bigint(20) DEFAULT NULL,
  `superchapterid` int(11) DEFAULT NULL,
  `superchaptername` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `types` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `visible` int(11) DEFAULT NULL,
  `zan` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wanandroid
-- ----------------------------
INSERT INTO `wanandroid` VALUES ('', 'xiaanming', '32', 'PopupWindow', '0', '13', '', '', '0', '180', 'http://blog.csdn.net/xiaanming/article/details/9121383', '2016-06-15', 'CSDN', '', '1465981907000', '30', '用户交互', ' PopupWindow的使用以及ArrayAdatper.notifyDataSetChanged()无效详解', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', 'King', '33', 'Notification', '0', '13', '', '', '0', '1472', 'http://iluhcm.com/2017/03/12/experience-of-adapting-to-android-notifications/', '2017-10-31', '', '', '1509450106000', '30', '用户交互', 'Android通知栏介绍与适配总结', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', 'Carson_Ho', '98', 'WebView', '0', '13', '', '', '0', '1766', 'http://www.jianshu.com/p/5e7075f4875f', '2018-03-18', '', '', '1521380786000', '98', '网络访问', 'Android：手把手教你构建 WebView 的缓存机制 & 资源预加载方案', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', 'Carson_Ho', '26', '基础UI控件', '0', '13', '', '', '0', '1810', 'http://www.jianshu.com/p/4fac6304d872', '2018-01-01', '', '', '1514802477000', '26', '常用控件', 'Android五大布局介绍&amp;属性设置大全', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', 'jia635', '26', '基础UI控件', '0', '13', '', '', '0', '2874', 'https://blog.csdn.net/jia635/article/details/52387658', '2018-04-26', '', '', '1524732906000', '26', '常用控件', '为什么Dialog不能用Application的Context', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', 'javalong', '98', 'WebView', '0', '13', '', '', '0', '3077', 'https://www.jianshu.com/p/ba62f39beadd', '2018-06-30', '', '', '1530324688000', '98', '网络访问', '编写一个JsBridge', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', '待有天晴时', '98', 'WebView', '0', '13', '', '', '0', '3108', 'https://www.jianshu.com/p/0f296881f541', '2018-07-05', '', '', '1530779052000', '98', '网络访问', 'Android WebView中的H5支付实践', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', 'jjlanbupt', '10', 'Activity', '0', '13', '', '', '0', '3205', 'https://www.jianshu.com/p/f0e53e770e32', '2018-07-31', '', '', '1533014688000', '10', '四大组件', '你可能不知道的Activity启动的诡异现象探索', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', '我没有三颗心脏', '321', '算法', '0', '13', '', '', '0', '3327', 'https://www.jianshu.com/p/3f0cd7af370d', '2018-08-27', '', '', '1535372977000', '245', 'Java深入', '【<em class=\'highlight\'>面试</em>必备】手撕代码，你怕不怕？', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', '达叔小生', '89', 'app缓存相关', '0', '13', '', '', '0', '3330', 'https://juejin.im/post/5b80b5adf265da43351d5eb2', '2018-08-28', '', '', '1535469413000', '89', '数据存储', '详解Android数据存储技术', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', '浪淘沙xud', '98', 'WebView', '0', '13', '', '', '0', '3372', 'https://www.jianshu.com/p/b66c225c19e2', '2018-09-09', '', '', '1536505406000', '98', '网络访问', 'Android WebView独立进程解决方案', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', '鸿洋', '408', '鸿洋', '0', '13', '', '', '0', '3632', 'http://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&amp;mid=2650826010&amp;idx=1&amp;sn=491e295e6a6c0fe450ad7aa91b6e97cc&amp;chksm=80b7b184b7c03892392015840e4ebc7f2c3533ce8c1a98a5dc6d6d3dd19d53562805d76f6dcb&amp;scene=38#wechat_redirect', '1天前', '', '', '1550672863000', '408', '公众号', '关于Binder，作为应用开发者你需要知道的全部 | 本周推荐', '0', '-1', '0', '0');
INSERT INTO `wanandroid` VALUES ('', '美团技术团队', '417', '美团技术团队', '0', '13', '', '', '0', '7149', 'http://mp.weixin.qq.com/s?__biz=MjM5NjQ5MTI5OA==&mid=2651748776&idx=3&sn=25684a8d5e561b10c450436bb1f9d35c&chksm=bd12a0e58a6529f3aad3ecd891bd2274aabfd19c82711298c88b96a6bd136c5522e9def3476a&scene=38#wechat_redirect', '2018-09-13', '', '', '1536768000000', '408', '公众号', 'ARKit：增强现实技术在美团到餐业务的实践', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', '美团技术团队', '417', '美团技术团队', '0', '13', '', '', '0', '7315', 'http://mp.weixin.qq.com/s?__biz=MjM5NjQ5MTI5OA==&mid=2651748960&idx=3&sn=93b468b875ee1e2d72c0a0c3464831a3&chksm=bd12a32d8a652a3b580b1ccac86c98204691dffa8ef1dbef3ac65fe7bca2ac9d6562a45aa501&scene=38#wechat_redirect', '2018-10-11', '', '', '1539187200000', '408', '公众号', '【基本功】 前端安全系列之二：如何防止CSRF攻击？', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', 'pengMaster', '294', '完整项目', '0', '13', '功能预览\r\n\r\n首页\r\n分类\r\n标签\r\n关于\r\n归档\r\n新增\r\n编辑\r\n聊天（正在添加）', 'http://wanandroid.com/resources/image/pc/default_project_img.jpg', '0', '7384', 'http://www.wanandroid.com/blog/show/2397', '2018-10-22', '', 'https://github.com/pengMaster/HexoBlog', '1540212139000', '294', '开源项目主Tab', 'Hexo 个人博客 前端+后台', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', 'yangchong211', '294', '完整项目', '0', '13', '自定义弹窗，其中包括：自定义Toast，采用builder模式，支持设置吐司多个属性；自定义dialog控件，仿IOS底部弹窗；自定义DialogFragment弹窗，支持自定义布局，也支持填充recyclerView布局；自定义PopupWindow弹窗，轻量级，还有自定义Snackbar等等；还有自定义loading加载窗，简单便用。已用于多个正式项目中。分享了弹窗源码分析文章共7篇等等。', 'http://wanandroid.com/blogimgs/044626b3-8c12-4e5b-ad22-43e25318d491.png', '0', '7388', 'http://www.wanandroid.com/blog/show/2401', '2018-10-22', '', 'https://github.com/yangchong211/YCDialog', '1540212268000', '294', '开源项目主Tab', '自定义弹窗封装库 YCDialog', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', 'leiyun1993', '294', '完整项目', '0', '13', '开放api的目的是让大家都参与其中，所以这款app是我完全按照自己的思维去写的，UI想怎么写就怎么写，架构想怎么写就怎么写，又不是上班时间，别对自己有那么多的要求。只求更熟练的掌握Kotlin和打发多余的时间，当然这个项目能给予大家消遣或者给新手一些启发，那就是赚到了！', 'http://wanandroid.com/blogimgs/2ea9bfe2-257c-455c-aafa-2a00cea4ebf7.png', '0', '7419', 'http://www.wanandroid.com/blog/show/2407', '2018-10-28', '', 'https://github.com/leiyun1993/WanAndroid', '1540721802000', '294', '开源项目主Tab', '基于Kotlin的一款黑白系WanAndroid客户端', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', '互联网侦察', '421', '互联网侦察', '0', '13', '', '', '0', '7424', 'https://mp.weixin.qq.com/s/bMDy8_Kwr1MKpL6-NrSYFQ', '2018-10-14', '', '', '1539446400000', '408', '公众号', '【<em class=\'highlight\'>面试</em>现场】如何在10亿数中找出前1000大的数', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', 'hyzhan43', '294', '完整项目', '0', '13', '继上次用 kotlin 编写了 一款简单 豆瓣电影 app 后。体验到了kotlin 的魅力。加上这段时间学习了 MVP 模式、MVVM模式，心痒痒，就像做个 app 来练练手，正当犹豫要选择哪一种来练手的时候，无意中看见另一种的模式艺术图片应用 T-MVVM~ 感觉说的挺有道理的。好奇心驱使我去试一下这种模式，说干就干。', 'http://www.wanandroid.com/blogimgs/070d9f4a-2ceb-457a-bb12-f7d55b5cf900.png', '0', '7441', 'http://www.wanandroid.com/blog/show/2411', '2018-10-30', '', 'https://github.com/hyzhan43/PlayAndroid', '1540908634000', '294', '开源项目主Tab', '用心打造&mdash;&mdash;Kotlin 版玩Android', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', 'Tomzem', '294', '完整项目', '0', '13', '利用RecyclerView实现时间轴，支持自定义布局', 'http://wanandroid.com/blogimgs/695493be-73e5-4e4b-90b3-728ca2cc2eb4.png', '0', '7470', 'http://www.wanandroid.com/blog/show/2413', '2018-11-06', '', 'https://github.com/Tomzem/AndroidTimeAxis', '1541507531000', '294', '开源项目主Tab', 'Andorid时间轴控件，支持自定义布局', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', 'rcj60560', '294', '完整项目', '0', '13', '使用大大提供的api自己写的练手 ：)', 'http://wanandroid.com/blogimgs/a490b5ed-4118-4e6b-997f-1d892ce610fc.png', '0', '7473', 'http://www.wanandroid.com/blog/show/2416', '2018-11-06', '', 'https://github.com/rcj60560/wanandroid', '1541507673000', '294', '开源项目主Tab', 'wanandroid 练手开源项目', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', 'boiyun', '294', '完整项目', '0', '13', '一款基于鸿洋大神提供的玩Android Api的app。\r\n项目使用 MVP+Retrofit+RxJava+Glide+ARouter等一些很主流的框架', 'http://www.wanandroid.com/blogimgs/7949fdd4-81cd-4c26-a79e-686e65526ff3.png', '0', '7497', 'http://www.wanandroid.com/blog/show/2420', '2018-11-10', '', 'https://github.com/boiyun/WanAndroid', '1541843466000', '294', '开源项目主Tab', '一款WanAndroidApp MVP+Retrofit+RxJava+Glide+ARouter', '0', '-1', '0', '0');
INSERT INTO `wanandroid` VALUES ('', '谷歌开发者', '415', '谷歌开发者', '0', '13', '', '', '0', '7528', 'https://mp.weixin.qq.com/s/yrD8zUcnUs3LmNNhtWn4fw', '2018-11-19', '', '', '1542556800000', '408', '公众号', 'App 生存与壮大的五条原则', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', '互联网侦察', '421', '互联网侦察', '0', '13', '', '', '0', '7535', 'https://mp.weixin.qq.com/s/uPoruWUD-v_1YQf2KPO45w', '2018-11-18', '', '', '1542470400000', '408', '公众号', '【<em class=\'highlight\'>面试</em>现场】如何编程解决朋友圈个数问题？', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', '谷歌开发者', '415', '谷歌开发者', '0', '13', '', '', '0', '7536', 'https://mp.weixin.qq.com/s/Et13_trWiyRFdEXRzyNEdA', '2018-11-20', '', '', '1542643200000', '408', '公众号', '强化学习中的好奇与拖延', '0', '-1', '0', '0');
INSERT INTO `wanandroid` VALUES ('', 'Marksss', '294', '完整项目', '0', '13', '用Kotlin写的新手引导库，使用方便，轻松添加引导蒙层，没有繁杂的入参但定制功能很全，欢迎点赞和fork', 'http://www.wanandroid.com/blogimgs/c73a7b1e-cc06-421f-af6f-16cf42f05fbf.png', '0', '7550', 'http://www.wanandroid.com/blog/show/2424', '2018-11-22', '', 'https://github.com/Marksss/easy-guide-android', '1542900118000', '294', '开源项目主Tab', '新手引导库EasyGuide', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', 'Kanghanbin', '294', '完整项目', '0', '13', 'WanAndroid，一款每日推荐优质文章App,项目涉及API均来自鸿洋大神的玩Android，目前所有api都有在app上体现。出于学习的目的做了这款Material Design风格的简洁的阅读应用，玩Android是一个每日推荐20~30篇Android优质文章的站点,提供Android常用工具和常用网站,并以知识体系的形式展现所有的文章分类。\r\n', 'http://www.wanandroid.com/blogimgs/aa8a434f-8195-4966-8b3a-07bf86da1e48.png', '0', '7553', 'http://www.wanandroid.com/blog/show/2427', '2018-11-22', '', 'https://github.com/Kanghanbin/wanAndroid', '1542900220000', '294', '开源项目主Tab', 'WanAndroid，一款每日推荐优质文章App', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', 'songmao123', '294', '完整项目', '0', '13', '一款数据基于Wan Android API，采用Kotlin+MVP+Dagger2+Rxjava架构的Material Design风格玩安卓客户端。', 'http://www.wanandroid.com/blogimgs/4c47aec3-1740-4ad9-9a37-ee99a1e742de.png', '0', '7555', 'http://www.wanandroid.com/blog/show/2429', '2018-11-22', '', 'https://github.com/songmao123/WanAndroid', '1542900280000', '294', '开源项目主Tab', 'Kotlin+MVP+RxJava+Dagger2版玩安卓客户端', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', 'littledavid-tech', '294', '完整项目', '0', '13', '这个算是对Android学习总结，MVP架构+好多轮子', 'http://wanandroid.com/blogimgs/9be242c9-e53e-4a54-9f49-d69b04b463b9.png', '0', '7641', 'http://www.wanandroid.com/blog/show/2449', '2018-12-11', '', 'https://github.com/littledavid-tech/WanAndroidApp', '1544499146000', '294', '开源项目主Tab', '我的涂鸦之作WanAndroid第三方客户端', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', '鸿洋', '408', '鸿洋', '0', '13', '', '', '0', '7672', 'https://mp.weixin.qq.com/s/r9erOzjVNg8WJ6zTfvMzRw', '2018-12-18', '', '', '1545062400000', '408', '公众号', '忍不住推荐一个作者给大家', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', '鸿洋', '408', '鸿洋', '0', '13', '', '', '0', '7688', 'https://mp.weixin.qq.com/s/6ESjR_vDP_SYICfQJv3Rsw', '2018-12-24', '', '', '1545580800000', '408', '公众号', '亲，还在为PopupWindow烦恼吗？', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', 'chinalwb', '294', '完整项目', '0', '13', '目前支持的样式:\r\n加粗\r\n斜体\r\n下划线\r\n删除线\r\n有序列表\r\n无序列表\r\n左对齐\r\n居中对齐 \r\n右对齐\r\n插入图片\r\n文字背景色\r\n插入超链接 \r\n@功能\r\n引用\r\n文字颜色（前景色）\r\n插入表情\r\n上角标\r\n下角标\r\n字体大小\r\n插入视频\r\n插入网络图片\r\n插入分割线\r\n所有样式均支持导出HTML文件\r\n加载HTML内容并继续编辑或显示', 'http://wanandroid.com/blogimgs/92d3f901-29c9-4c5f-9bee-cfd028e81d49.png', '0', '7694', 'http://www.wanandroid.com/blog/show/2455', '2018-12-24', '', 'https://github.com/chinalwb/Android-Rich-text-Editor', '1545653416000', '294', '开源项目主Tab', '基于自定义Span的富文本编辑器', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', 'gaoleicoding', '294', '完整项目', '0', '13', '学Android 主要是采用 MVP + RxJava2 + Retrofit + Multimodule 等架构设计。利用开源的api获取有关android知识的数据，非常感谢张鸿洋老师提供的开放api！向开源者致敬！项目中把目前流行的项目框架都集成到了，代码结构清晰并且有详细注释，如有建议或疑问可留言反馈 。', 'http://wanandroid.com/blogimgs/056802c7-9a43-4081-9482-35d0c4e1a9fc.png', '0', '7722', 'http://www.wanandroid.com/blog/show/2463', '2019-01-03', '', 'https://github.com/gaoleicoding/AndroidLearnProject', '1546516414000', '294', '开源项目主Tab', '结合android当前著名框架写的一个项目--学Android', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', 'guofudong', '294', '完整项目', '0', '13', ' MVP + Retrofit + RxKotlin + Dagger2实现的一款用Kotlin语言编写的多媒体类应用。', 'http://wanandroid.com/blogimgs/df0865d9-bda6-4917-9783-c58eb07f930d.png', '0', '7724', 'http://www.wanandroid.com/blog/show/2465', '2019-01-03', '', 'https://github.com/guofudong/KotlinAndroid', '1546516773000', '294', '开源项目主Tab', '基于组件化 + MVP + Retrofit + RxKotlin + Dagger2实现的一款用Kotlin语言编写的影视类应用', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', 'yangchong211', '294', '完整项目', '0', '13', 'flutter学习案例，接口使用玩Android开放的api，作为新手入门训练代码案例，已经完成了基本的功能。努力打造一个体验好的flutter版本的玩android客户端！ ', 'http://wanandroid.com/blogimgs/859a48f2-9ed9-4c13-b12b-23e1827ee103.png', '0', '7728', 'http://www.wanandroid.com/blog/show/2469', '2019-01-03', '', 'https://github.com/yangchong211/ycflutter', '1546517070000', '294', '开源项目主Tab', 'flutter版本的玩Android客户端', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', 'arvinljw', '294', '完整项目', '0', '13', 'Retrofit+LiveData 封装网络请求，DrawerLayout+BottomNavigationView+Fragment 实现侧边栏加底部导航的App整体结构，SwipeRefreshLayout+BaseQuickAdapter实现列表的下拉刷新和上拉加载，TabLayout+ViewPager+Fragment实现顶部导航布局；更多功能项目吧。', 'http://wanandroid.com/blogimgs/4384c478-c3a5-4c0a-b7a1-a5e48c772ebf.png', '0', '7735', 'http://www.wanandroid.com/blog/show/2476', '2019-01-03', '', 'https://github.com/arvinljw/WanAndroid', '1546517324000', '294', '开源项目主Tab', 'WanAdnroid练手项目', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', '鸿洋', '408', '鸿洋', '0', '13', '', '', '0', '7741', 'https://mp.weixin.qq.com/s/-QFLbKOxr6HmAFqFlPHwRQ', '2019-01-03', '', '', '1546444800000', '408', '公众号', '2018年终总结（兼个人详历）', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', 'chinalwb', '294', '完整项目', '0', '13', '用组件化的思想实现一个玩Android APP。目前首页、项目、公众号分别以组件的形式显示到了app中。项目地址附上了实现步骤，欢迎实现您自己的组件！欢迎提出问题 欢迎讨论 欢迎赐教。', 'http://www.wanandroid.com/blogimgs/76eb003b-7db1-4e00-be3d-3076b48afc8c.png', '0', '7775', 'http://www.wanandroid.com/blog/show/2483', '2019-01-13', '', 'https://github.com/chinalwb/wan_android', '1547364225000', '294', '开源项目主Tab', '组件化的玩Android App', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', 'xxq2dream', '10', 'Activity', '0', '13', '', '', '0', '7784', 'https://juejin.im/post/5c19a1236fb9a04a102f39aa', '2019-01-13', '', '', '1547368482000', '10', '四大组件', 'Fragment中调用startActivityForResult的那些坑', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', '鸿洋', '408', '鸿洋', '0', '13', '', '', '0', '7793', 'https://mp.weixin.qq.com/s/VBZnrhhawAKoaL-eCQKCQQ', '2019-01-11', '', '', '1547136000000', '408', '公众号', '知乎里的一些特效实现 无缝拖拽的 Layout', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', '鸿洋', '408', '鸿洋', '0', '13', '', '', '0', '7821', 'https://mp.weixin.qq.com/s/QRGj4rhQQ_rdlOnNOGCDJw', '2019-01-16', '', '', '1547568000000', '408', '公众号', 'RecyclerView 使用总结以及常见问题解决方案', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', '鸿洋', '408', '鸿洋', '0', '13', '', '', '0', '7822', 'https://mp.weixin.qq.com/s/v3cvovaDCBkith63vQPTAA', '2019-01-18', '', '', '1547740800000', '408', '公众号', '一次失败的RecycleView滑动定位，SnapHelper 真香！', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', '鸿洋', '408', '鸿洋', '0', '13', '', '', '0', '7823', 'https://mp.weixin.qq.com/s/orLlI1SW4U09Jqzim6hSaA', '2019-01-19', '', '', '1547827200000', '408', '公众号', '2018年终总结 | 祝大家有编码，更有人生。', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', '承香墨影', '411', '承香墨影', '0', '13', '', '', '0', '7834', 'https://mp.weixin.qq.com/s/E5t4tOQ9acmIgQ8cSxPjaQ', '2019-01-21', '', '', '1548000000000', '408', '公众号', '只有 32 位的 IPv4，为何依然够用？| 网络协议之 CIDR', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', 'kingwang666', '294', '完整项目', '0', '13', 'APP信息是一个免费的工具应用. 它有以下功能特点:\r\n\r\n查看已安装的app信息.\r\n查看未安装的apk信息.\r\n导出已安装的app应用的apk文件.\r\n复制apk的签名信息到剪切板.', 'http://wanandroid.com/blogimgs/f16b7060-38e2-4ebd-87d9-d61b59a000e2.png', '0', '7892', 'http://www.wanandroid.com/blog/show/2493', '2019-01-23', '', 'https://github.com/kingwang666/GetApk', '1548247914000', '294', '开源项目主Tab', '一个可以显示app或者apk信息。并且可导出已安装的app的apk文件工具应用', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', '承香墨影', '411', '承香墨影', '0', '13', '', '', '0', '7910', 'https://mp.weixin.qq.com/s/whYh__5bLKe7aDYm73LCgA', '2019-01-28', '', '', '1548604800000', '408', '公众号', '设计师小姐姐给的 Lottie 动画中&ldquo;带图片&rdquo;，如何预览？', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', '鸿洋', '408', '鸿洋', '0', '13', '', '', '0', '7912', 'https://mp.weixin.qq.com/s/AUEDB--AHy4kLUHnMzjFYg', '2019-01-25', '', '', '1548345600000', '408', '公众号', '图文搞懂 RecyclerView 刷新机制 | 源码分析', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', '鸿洋', '408', '鸿洋', '0', '13', '', '', '0', '7913', 'https://mp.weixin.qq.com/s/Ew6gHeHp7rFuy-4RfU7RPQ', '2019-01-28', '', '', '1548604800000', '408', '公众号', '通用的Android练习项目模板配置「常用工具类，项目结构，模板使用」', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', '鸿洋', '408', '鸿洋', '0', '13', '', '', '0', '7917', 'https://mp.weixin.qq.com/s/loIoTV7nZ9su3n02bfL5aA', '2019-01-29', '', '', '1548691200000', '408', '公众号', 'Android开发之仿微博贴纸效果实现', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', '鸿洋', '361', '课程推荐', '0', '13', '', '', '0', '7920', 'https://mp.weixin.qq.com/s/RhWhMFj3Yec1hdZIdha-cw', '2019-01-31', '', '', '1548931566000', '249', '干货资源', '有什么是你<em class=\'highlight\'>面试</em>很多次都失败后才知道的？', '0', '-1', '0', '0');
INSERT INTO `wanandroid` VALUES ('', '鸿洋', '408', '鸿洋', '0', '13', '', '', '0', '7924', 'https://mp.weixin.qq.com/s/CrriyKC8nUgpx6exH8X1dA', '2019-01-31', '', '', '1548864000000', '408', '公众号', '年度合集 | 值得深入的文章汇总', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', '鸿洋', '408', '鸿洋', '0', '13', '', '', '0', '7925', 'https://mp.weixin.qq.com/s/y5SaYprZJqhUmWy8a9qkKQ', '2019-02-01', '', '', '1548950400000', '408', '公众号', '最后一篇啦，快来快来~', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', '鸿洋', '408', '鸿洋', '0', '13', '', '', '0', '7936', 'https://mp.weixin.qq.com/s/U9p_oHSrskn-gORtzX6-oA', '2019-02-12', '', '', '1549900800000', '408', '公众号', '开工大吉 | 技术面试九忌', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', '鸿洋', '408', '鸿洋', '0', '13', '', '', '0', '7945', 'https://mp.weixin.qq.com/s/JvlTnZJGSESpPEwYJF1XNg', '2019-02-14', '', '', '1550073600000', '408', '公众号', '&ldquo;阿里为大家学习Flutter操碎了心&rdquo;', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', '鸿洋', '408', '鸿洋', '0', '13', '', '', '0', '7957', 'https://mp.weixin.qq.com/s/ya0RiLuHfIBrPLkl2lTbaA', '2019-02-18', '', '', '1550419200000', '408', '公众号', '&ldquo;丧心病狂&rdquo;的混淆操作！', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', '鸿洋', '408', '鸿洋', '0', '13', '', '', '0', '7958', 'https://mp.weixin.qq.com/s/1PoO7DXBm8kddaPAhpBkZQ', '2天前', '', '', '1550592000000', '408', '公众号', '&ldquo;啥是佩奇？&rdquo; Android 开发者眼里的佩奇', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', 'code小生', '414', 'code小生', '0', '13', '', '', '0', '7962', 'https://mp.weixin.qq.com/s/l88aZR6OifBcKmjzmgCyNg', '2019-02-18', '', '', '1550419200000', '408', '公众号', '字节跳动Android实习<em class=\'highlight\'>面试</em>凉凉经', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', '鸿洋', '408', '鸿洋', '0', '13', '', '', '1', '7972', 'https://mp.weixin.qq.com/s/nn-nwXnRI9JYSmknH1pzYg', '18小时前', '', '', '1550764800000', '408', '公众号', '再&ldquo;丧心病狂&rdquo;的混淆也不怕', '0', '-1', '1', '0');
INSERT INTO `wanandroid` VALUES ('', '承香墨影', '411', '承香墨影', '0', '13', '', '', '0', '7975', 'https://mp.weixin.qq.com/s/4Tg_NsXS8Z4DQPBIxwJplg', '2019-02-20', '', '', '1550592000000', '408', '公众号', '图解：单链表删除，不遍历链表也能做（时间复杂度O(1)）', '0', '-1', '1', '0');
