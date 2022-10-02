const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
      app.use(
          '/',
          createProxyMiddleware({
              target: 'http://ec2-3-16-183-72.us-east-2.compute.amazonaws.com/',
      changeOrigin: true,
    })
  );
};