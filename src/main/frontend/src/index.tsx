import React, { Suspense } from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import Loading from './Loading';

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      suspense: false,
  }
}});


const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render(
  <QueryClientProvider client={queryClient}>
    <Suspense fallback={<Loading />}>
      <App />
    </Suspense>
  </QueryClientProvider>
);

