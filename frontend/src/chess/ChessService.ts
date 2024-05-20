import axios from "axios";
import type {GameModel} from "@/chess/models/GameModel";
import type {RouteParamValue} from "vue-router";
import type {SquareModel} from "@/chess/models/SquareModel";

export class ChessService {
    public static async createGame(): Promise<string>{
        try {
            const response = await axios.get('http://localhost:8080/chess')
            return response.data
        } catch (e) {
            return ""
        }
    }

    public static async getBoard(id: string): Promise<GameModel> {
        try {
            const response = await axios.get(
                `http://localhost:8080/chess/getGame`,
                {
                    params: {
                        id: id
                    }
                })
            return response.data
        }
        catch (e) {
            throw e
        }
    }

    public static async getPossibleSquaresToMove(id: string, rank: number, file: number)
    : Promise<SquareModel[]>{
        try {
            const response = await axios.get(
                `http://localhost:8080/chess/getSquaresToMove`,
                {
                    params: {
                        id: id,
                        rank: rank,
                        file: file
                    }

                })

            return response.data
        }
        catch (e) {
            console.error(e)
            return []
        }
    }

    static async makeMove(id: string, startSquare: SquareModel, endSquare: SquareModel)
        : Promise<boolean> {
        try {
            const response = await axios.put(
                'http://localhost:8080/chess/makeMove',
                {
                    id: id,
                    startSquare: startSquare,
                    endSquare: endSquare,
                }
            )
            return response.data;
        }
        catch (e) {
            console.error(e)
            return false
        }
    }
}