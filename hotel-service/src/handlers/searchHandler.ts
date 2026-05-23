import { Context } from 'hono';
import { SearchService } from '../service/SearchService.ts';

export const serveSearchedHotel = async (context: Context) => {
	const city = context.req.query('city');
	const searchService = context.get('searchService') as SearchService;

	if (city === undefined || city.length === 0) {
		return context.json(
			{ success: false, error: { message: 'City name cannot be empty' } },
			500,
		);
	}

	const hotels = await searchService.findHotelsByCity(city);
	return context.json({ success: true, data: hotels }, 200);
};
