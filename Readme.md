# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Github Jpos documentation](https://github.com/jpos/jPOS/blob/master/doc/src/asciidoc/ch08/channel_adaptor.adoc)
* [Jpos Reference Guide](http://jpos.org/doc/proguide-draft.pdf)


### Guides

This project is used to build an ESB to route financial transaction with Iso 8583 massages :

We used for this purpose: 
* [Building a RESTful Web Service using Spring Boot](https://spring.io/guides/gs/rest-service/)
* [Initiate a ISO 8583 msg using Spring Boot](https://medium.com/@nicklimvs/jpos-how-to-initiate-message-from-qserver-faa960c1d1cd)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Additional Links

These additional references should also help you:

* [Iso 8583 with Spring Boot by Nick Lin](https://www.youtube.com/watch?v=G9-AhOU3wfA&list=PL9buZkTKhIT4RgGV20yE3LSbscMimkgrX&index=8)

### Testing code

To test our code, you can configured the file esb_chanel (the host name and the port) to where you want to route the transaction.

Our code is supposed to understand Visa transaction, So to test it, you can used your PC as localhost client with the [Putty Application with telnet connection](https://www.ssh.com/academy/ssh/putty/download)
* __Port:__ 12201
* __Host Name:__ localhost

You do this connection after launching the SpringBoot Application.

Then send the follow Iso 8583 Message using connection type equals to: 
* __Others ---> Raw__

After running putty with the good configuration, use the follow message to test our code:

```xml
<isomsg >
  <field id="0" value="1804"/>
  <field id="11" value="000000000001"/>
  <field id="12" value="20190912121040"/>
  <field id="24" value="831"/>
  <field id="32" value="627765"/>
  <field id="41" value="00627765"/>
  <field id="59" value="EBLECHO"/>
  <field id="93" value="627765"/>
  <field id="94" value="627766DC"/>
  <field id="123" value="DC"/>
</isomsg>
```
__NB:__ We are opened to any contribution for the project.

