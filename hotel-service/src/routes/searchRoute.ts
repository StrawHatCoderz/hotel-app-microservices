import { Hono } from 'hono';
import { serveSearchedHotel } from '../handlers/searchHandler.ts';

export const createSearchRoute = () => {
	const searchRoute = new Hono();

	searchRoute.get('/hotels', serveSearchedHotel);

	return searchRoute;
};
