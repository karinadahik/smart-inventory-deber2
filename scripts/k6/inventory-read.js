import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  vus: 2,
  duration: '30s',

  thresholds: {
    http_req_duration: ['p(95)<1000'],
    http_req_failed: ['rate<0.01'],
    checks: ['rate>0.99'],
  },

  summaryTrendStats: [
    'avg', 'min', 'med', 'max', 'p(90)', 'p(95)'
  ],
};

export default function () {
  const baseUrl = __ENV.BASE_URL ||
    'http://127.0.0.1:8080';

  const response = http.get(
    `${baseUrl}/api/v1/inventory`,
    {
      headers: {
        'X-Api-Key': __ENV.API_KEY,
      },
      timeout: '10s',
    }
  );

  check(response, {
    'Inventario responde HTTP 200':
      (r) => r.status === 200,
  });

  sleep(2);
}
