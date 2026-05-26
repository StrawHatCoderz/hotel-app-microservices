import { createClient } from 'redis';

export const redisClient = createClient({
	url: `redis://${Deno.env.get('REDIS_HOST')}:${Deno.env.get('REDIS_PORT')}`,
});

redisClient.on('error', (err: unknown) =>
	console.error('Redis Client Error', err),
);
redisClient.on('connect', () => console.log('Connected to Redis'));

(async () => {
	await redisClient.connect();
})();
