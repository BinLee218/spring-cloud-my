# vue-admin-template
login   
登录的时候输入密码后  handleLogin()首先做验证，验证通过了，this.$store.dispatch调用action。   
action的类型是user/login，通过跟踪了解含义是，根据这个store=>index.js里面定义的modules来找的，   
先是user，找对应的js，然后再找对应js里面定义的acitons下的login然后异步调用后端api站点接口   
随后的话应该是要进入首页，在这个login成功之后this.$router.push指定了一个地址，默认是"/"，然后去route=>index.js里面找到对应的path   
找到了之后去渲染vue页面，不过在渲染之前因为有这个router.beforeEach的存在，所以进入页面之前要首先去执行这里的逻辑   
根据逻辑发现又调用了user/getInfo接口，同样的也是通过store.dispatch来操作的
