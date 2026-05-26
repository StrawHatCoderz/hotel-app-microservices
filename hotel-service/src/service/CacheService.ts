export class CacheService {
	#redisClient;

	constructor(redisClient: any) {
		this.#redisClient = redisClient;
	}

	async get(key: string) {
		try {
			const value = await this.#redisClient.get(key);
			return value ? JSON.parse(value) : null;
		} catch (error) {
			console.error(`Error getting cache for key ${key}:`, error);
			return null;
		}
	}

	async set(key: string, value: any, ttlSeconds: number) {
		try {
			const stringValue = JSON.stringify(value);

			await this.#redisClient.setEx(key, ttlSeconds, stringValue);
		} catch (error) {
			console.error(`Error setting cache for key ${key}:`, error);
		}
	}

	async del(key: string) {
		try {
			await this.#redisClient.del(key);
		} catch (error) {
			console.error(`Error deleting cache for key ${key}:`, error);
		}
	}
}
