import { Context, Hono } from 'hono';
import { logger } from 'hono/logger';
import { Next } from 'hono/types';
import { createInternalRoute } from './routes/internalRoute.ts';
import { createSearchRoute } from './routes/searchRoute.ts';
import { HotelService } from './service/HotelService.ts';
import { SearchService } from './service/SearchService.ts';

export const createApp = ({
	searchService,
	hotelService,
}: {
	searchService: SearchService;
	hotelService: HotelService;
}) => {
	const app = new Hono();

	app.use(logger());

	app.use('*', async (c: Context, next: Next) => {
		c.set('searchService', searchService);
		c.set('hotelService', hotelService);
		await next();
	});

	app.get('/', (c) => c.json('hello world'));
	app.route('/internal', createInternalRoute());
	app.route('/search', createSearchRoute());

	return app;
};
