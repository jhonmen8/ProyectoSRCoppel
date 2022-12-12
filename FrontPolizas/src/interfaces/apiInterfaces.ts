export interface RequestProps {
    endpoint: string;
    body?: object;
    method?: string;
    token?: boolean;
    multi_part?: boolean;
}