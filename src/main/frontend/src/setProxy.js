const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
      app.use(
          '/',
          createProxyMiddleware({
            target: 'http://ec2-18-221-179-218.us-east-2.compute.amazonaws.com:8080',
      changeOrigin: true,
    })
  );
};