import { Hono } from 'hono';
import { serveHotelData, updateHotelData } from '../handlers/internalHandler.ts';

export const createInternalRoute = () => {
	const route = new Hono();

	route.get('/hotel/:hotelId', serveHotelData);
	route.post('/hotel/update/:hotelId', updateHotelData);

	return route;
};
